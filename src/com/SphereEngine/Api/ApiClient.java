package com.SphereEngine.Api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

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
		this.userAgent = "SphereEngine";
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
	 * @throws ClientErrorException on a non 4xx response
	 * @return API response
	 */
	public JsonObject callApi(String resourcePath, String method, Map<String, String> urlParams, Map<String, String> queryParams, Map<String, String> postData, Map<String, String> headerParams, String responseType)
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl).register(String.class);
		
		// replacing variables in API resource PATH
		if (urlParams != null) {
			for (Map.Entry<String, String> entry : urlParams.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    resourcePath = resourcePath.replace("{"+key+"}", value);
			}
		}

		target = target
				.path(resourcePath)
				.queryParam("access_token", accessToken);
		
		// set query parameters
		if (queryParams != null) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    target = target.queryParam(key, value);
			}
		}
	 
		Builder builder = target
	            .request(MediaType.APPLICATION_JSON)
	            .header("User-Agent", userAgent);
	            //.header("Content-Type", "application/x-www-form-urlencoded");
		
		// set form parameters
		Form form = new Form();
		if (postData != null) {
			for (Map.Entry<String, String> entry : postData.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    form.param(key, value);
			}
		}
		
		String response = "";
		
		if ("GET".equals(method)) {
			response = builder.get(String.class);
		} else if ("POST".equals(method)) {
			response = builder.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		} else if ("PUT".equals(method)) {
			response = builder.put(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		} else if ("DELETE".equals(method)) {
			response = builder.delete(String.class);
		}
		
		JsonParser parser = new JsonParser();
		JsonObject result = new JsonObject();
		
		if ("file".equals(responseType)) {
			result.addProperty("file_content", response);
		} else if (! "PUT".equals(method)) {
			if (parser.parse(response).isJsonObject()) {
				result = parser.parse(response).getAsJsonObject();
			}
		} else {
			// empty JSON for PUT methods
		}
		
		return result;
	}
	
}
