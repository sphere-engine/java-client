package com.SphereEngine.Api;

import java.util.Map;

import javax.ws.rs.NotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;

/*
 * @author	Sphere Research Labs Sp z o.o.
 * @version	0.1
 * @since	01/18/2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class CompilersClientV3
{
	/**
	 * Instance of the ApiClient 
	 */
	private ApiClient apiClient;
	
	/**
	 * Module name of the API
	 */
	private String module = "compilers";
	
	/**
	 * Version of the API
	 */
	private String version = "v3";
	
	/**
	 * Constructor
	 * 
	 * @param {String} accessToken - Sphere Engine Compilers access token
	 * @param {String} endpoint - Sphere Engine Compilers endpoint
	 */
	public CompilersClientV3(String accessToken, String endpoint)
	{
		apiClient = new ApiClient(accessToken, createEndpointLink(endpoint));
	}
	
	/**
	 * Create a complete API endpoint url
	 * 
	 * @param {String} endpoint - partial endpoint
	 * @return Complete API endpoint url
	 */
	private String createEndpointLink(String endpoint)
	{
		if (!endpoint.contains(".")) {
			return endpoint + "." + module + "." + "sphere-engine.com/api/" + version;
		} else {
			return endpoint + "/api/" + version;
		}
	}
	
	/**
	 * Test method
	 *
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject test()
	{
	    return apiClient.callApi("/test", "GET", null, null, null, null, null);
	}
	
	/**
	 * List of all compilers
	 *
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject getCompilers()
	{
	    return apiClient.callApi("/compilers", "GET", null, null, null, null, null);
	}

	/**
	 * Create a new submission
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {string} input - data that will be given to the program on stdin
	 * @param {integer} priority - priority of the submission
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject createSubmission(String source, Integer compiler, String input, Integer priority)
	{
		Map<String, String> postParams = new HashMap<String,String>();
		postParams.put("sourceCode", source);
		postParams.put("language", compiler.toString());
		postParams.put("input", input);
		if (priority != null) postParams.put("priority", priority.toString());

		return apiClient.callApi("/submissions", "POST", null, null, postParams, null, null);
	}
	
	/**
	 * Create a new submission with normal priority
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {string} input - data that will be given to the program on stdin
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject createSubmission(String source, Integer compiler, String input)
	{
		return createSubmission(source, compiler, input, null);
	}

	/**
	 * Create a new submission with normal priority and empty input
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject createSubmission(String source, Integer compiler)
	{
		return createSubmission(source, compiler, "", null);
	}
	
	/**
	 * Create a new C++ submission with normal priority and empty input
	 *
	 * @param {string} source - source code
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject createSubmission(String source)
	{
		return createSubmission(source, 1, "", null);
	}

	/**
	 * Create an empty C++ submission with normal priority and empty input
	 *
	 * @param {string} source - source code
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject createSubmission()
	{
		return createSubmission("", 1, "", null);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission id
	 * @param {boolean} withSource - determines whether source code of the submission should be returned
	 * @param {boolean} withInput - determines whether input data of the submission should be returned
	 * @param {boolean} withOutput - determines whether output produced by the program should be returned
	 * @param {boolean} withStderr - determines whether stderr should be returned
	 * @param {boolean} withCmpinfo - determines whether compilation information should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id, Boolean withSource, Boolean withInput, Boolean withOutput, Boolean withStderr, Boolean withCmpinfo)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> queryParams = new HashMap<String,String>();
		
		urlParams.put("id", id.toString());
		
		queryParams.put("withSource", (withSource) ? "1" : "0");
		queryParams.put("withInput", (withInput) ? "1" : "0");
		queryParams.put("withOutput", (withOutput) ? "1" : "0");
		queryParams.put("withStderr", (withStderr) ? "1" : "0");
		queryParams.put("withCmpinfo", (withCmpinfo) ? "1" : "0");

		return apiClient.callApi("/submissions/{id}", "GET", urlParams, queryParams, null, null, null);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission id
	 * @param {boolean} withSource - determines whether source code of the submission should be returned
	 * @param {boolean} withInput - determines whether input data of the submission should be returned
	 * @param {boolean} withOutput - determines whether output produced by the program should be returned
	 * @param {boolean} withStderr - determines whether stderr should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id, Boolean withSource, Boolean withInput, Boolean withOutput, Boolean withStderr)
	{
		return getSubmission(id, withSource, withInput, withOutput, withStderr, false);
	}

	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission id
	 * @param {boolean} withSource - determines whether source code of the submission should be returned
	 * @param {boolean} withInput - determines whether input data of the submission should be returned
	 * @param {boolean} withOutput - determines whether output produced by the program should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id, Boolean withSource, Boolean withInput, Boolean withOutput)
	{
		return getSubmission(id, withSource, withInput, withOutput, false, false);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission id
	 * @param {boolean} withSource - determines whether source code of the submission should be returned
	 * @param {boolean} withInput - determines whether input data of the submission should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id, Boolean withSource, Boolean withInput)
	{
		return getSubmission(id, withSource, withInput, false, false, false);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission id
	 * @param {boolean} withSource - determines whether source code of the submission should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id, Boolean withSource)
	{
		return getSubmission(id, withSource, false, false, false, false);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission id
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id)
	{
		return getSubmission(id, false, false, false, false, false);
	}
	
	/**
	 * Fetches status of multiple submissions (maximum 20 ids)
	 *
	 * @param {integer[]} ids - Submission ids (required)
	 * @throws NotAuthorizedException for invalid access token
	 * @return API response
	 */
	public JsonObject getSubmissions(Integer[] ids)
	{
		Map<String, String> queryParams = new HashMap<String,String>();
		
		StringBuilder idsStringBuilder = new StringBuilder();
		for(Integer id: ids) {
			if(id == null || id <= 0) {
				break;
			}
			idsStringBuilder.append(id);
			idsStringBuilder.append(",");
		}

		String idsString = "";
		if(idsStringBuilder.length() > 0) {
			idsString = idsStringBuilder.substring(0, idsStringBuilder.length() - 1);
		}
		queryParams.put("ids", idsString);
		
		return apiClient.callApi("/submissions", "GET", null, queryParams, null, null, null);
	}
	
	/**
	 * Fetch raw stream
	 *
	 * @param {integer} id - Submission id
	 * @param {string} stream -  name of the stream, input|output|stderr|cmpinfo|source
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission or non existing stream
	 * @return file content
	 */
	public String getSubmissionStream(Integer id, String stream)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		List<String> streams = Arrays.asList("input", "stdin", "output", "stdout", "stderr", "error", "cmpinfo", "source");

		if (!streams.contains(stream)) {
			throw new NotFoundException("stream doesn't exist");
		}
		
		urlParams.put("id", id.toString());
		urlParams.put("stream", stream.toString());

		return apiClient.
			callApi("/submissions/{id}/{stream}", "GET", urlParams, null, null, null, "file")
			.get("file_content")
			.getAsString();
	}
}
