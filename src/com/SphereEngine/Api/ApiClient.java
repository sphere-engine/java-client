package com.SphereEngine.Api;

import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ConflictException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.SphereEngine.Api.Exception.ForbiddenException;
import com.SphereEngine.Api.Exception.NotAuthorizedException;
import com.SphereEngine.Api.Exception.NotFoundException;
import com.SphereEngine.Api.Exception.ClientException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class ApiClient 
{
	private String baseUrl;
	private String accessToken;
	private String userAgent;
	private String PROTOCOL = "http";
	
    /**
     * Constructor
     * @param string accessToken Access token to Sphere Engine service
     * @param string version version of the API
     * @param string endpoint link to the endpoint
     */
	public ApiClient(String accessToken, String endpoint)
	{
		this.accessToken = accessToken;
		this.baseUrl = buildBaseUrl(endpoint);
		this.userAgent = "SphereEngine/ClientJava";
	}

	private String buildBaseUrl(String endpoint)
	{
		return PROTOCOL + "://" + endpoint;
	}
	
	/**
	 * Make the HTTP call
	 * 
	 * @param string resourcePath path to method endpoint
	 * @param string method       method to call
	 * @param array  queryParams  parameters to be place in query URL
	 * @param array  postData     parameters to be placed in POST body
	 * @param array  headerParams parameters to be place in request header
	 * @param string responseType expected response type of the endpoint
	 * @throws ClientException 
	 * @throws ConnectionException 
	 * @return API response
	 */
	public JsonObject callApi(String resourcePath, String method, Map<String, String> urlParams, Map<String, String> queryParams, Map<String, String> postData, Map<String, String> headerParams, String responseType) throws ClientException, ConnectionException
	{
		OkHttpClient client = new OkHttpClient();
		
		// replacing variables in API resource PATH
		if (urlParams != null) {
			for (Map.Entry<String, String> entry : urlParams.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    resourcePath = resourcePath.replace("{"+key+"}", value);
			}
		}
		
		HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
		urlBuilder.addPathSegments(resourcePath.substring(1));
		urlBuilder.addQueryParameter("access_token", accessToken);
		
		// set query parameters
		if (queryParams != null) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    urlBuilder.addQueryParameter(key, value);
			}
		}
		String url = urlBuilder.build().toString();
		
		// set form parameters
		FormBody.Builder form = new FormBody.Builder();
		if (postData != null) {
			for (Map.Entry<String, String> entry : postData.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    form.add(key, value);
			}
		}
		RequestBody formBody = form.build();
		
		Request.Builder requestBuilder = new Request.Builder();
		requestBuilder
			.url(url)
			.header("User-Agent", userAgent);
		
		// set method
		if ("GET".equals(method)) {
			requestBuilder.get();
		} else if ("POST".equals(method)) {
			requestBuilder.post(formBody);
		} else if ("PUT".equals(method)) {
			requestBuilder.put(formBody);
		} else if ("DELETE".equals(method)) {
			requestBuilder.delete(formBody);
		}
		
		Request request = requestBuilder.build();
		
		try (Response response = client.newCall(request).execute()) {
			ResponseBody body = response.body();
			String content = "";
			if(body != null) {
				content = body.string();
			}
			JsonParser parser = new JsonParser();
			JsonObject result = new JsonObject();
			
			if (!response.isSuccessful()) {
				if (parser.parse(content).isJsonObject()) {
					result = parser.parse(content).getAsJsonObject();
				}
				
				if(response.code() >= 500 && response.code() < 600) {
					throw new ConnectionException(response.message(), response.code());
				}
				
				String message = (result.get("message") != null) ? result.get("message").getAsString() : "No message";
				if(response.code() == 400) {
					// bad request
					throw new BadRequestException(message);
					
				} else if(response.code() == 401) {
					// unauthorized access
					throw new NotAuthorizedException(message);
					
				} else if(response.code() == 403) {
					// forbidden access
					throw new ForbiddenException(message);
					
				} else if(response.code() == 404) {
					// not authorized
					throw new NotFoundException(message);
					
				} else if(response.code() == 409) {
					// conflict
					throw new ConflictException(message);
					
				} else {
					// other
					throw new ClientException(message, response.code());
					
				}
			}
			
			if ("file".equals(responseType)) {
				result.addProperty("file_content", content);
			} else if (! "PUT".equals(method)) {
				if (parser.parse(content).isJsonObject()) {
					result = parser.parse(content).getAsJsonObject();
				}
			} else {
				// empty JSON for PUT methods
			}
			
			return result;
			
		} catch (IOException e) {
			throw new ConnectionException(e.getMessage(), 0);
	    }
	}
	
}
