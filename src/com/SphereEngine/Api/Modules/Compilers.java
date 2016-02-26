package com.SphereEngine.Api.Modules;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonObject;

import com.SphereEngine.Api.ApiClient;
import com.SphereEngine.Api.Settings;

/*
 * @author	Sphere Research Labs Sp z o.o.
 * @version	0.1
 * @since	01/18/2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class Compilers
{
	/**
	 * Instance of the ApiClient 
	 */
	private ApiClient apiClient;
	
	/**
	 * Constructor
	 * 
	 * @param {Settings} settings - API settings object
	 */
	public Compilers(Settings settings)
	{
		String accessToken = settings.getCompilersAccessToken();
		String version = settings.getCompilersVersion();
		String endpoint = settings.getCompilersEndpoint();
		
		apiClient = new ApiClient(accessToken, createEndpointLink(version, endpoint));
	}
	
	/**
	 * Create a complete API endpoint url
	 * 
	 * @param {String} version - API version
	 * @param {String} endpoint - partial endpoint
	 * @return Complete API endpoint url
	 */
	private String createEndpointLink(String version, String endpoint)
	{
		if (endpoint == null) {
			return "api.compilers.sphere-engine.com/api/" + version; 
		} else {
			return endpoint + ".api.compilers.sphere-engine.com/api/" + version;
		}
	}
	
	/**
	 * Test method
	 *
	 * @return API response
	 */
	public JsonObject test()
	{
	    return apiClient.callApi("/test", "GET", null, null, null, null, null);
	}
	
	/**
	 * List of all compilers
	 *
	 * @return API response
	 */
	public JsonObject getCompilers()
	{
	    return apiClient.callApi("/languages", "GET", null, null, null, null, null);
	}

	/**
	 * Create a new submission
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {string} input - data that will be given to the program on stdin
	 * @return API response
	 */
	public JsonObject createSubmission(String source, Integer compiler, String input)
	{
		Map<String, String> postParams = new HashMap<String,String>();
		postParams.put("sourceCode", source);
		postParams.put("language", compiler.toString());
		postParams.put("input", input);

		return apiClient.callApi("/submissions", "POST", null, null, postParams, null, null);
	}

	/**
	 * Create a new submission with empty input
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @return API response
	 */
	public JsonObject createSubmission(String source, Integer compiler)
	{
		return createSubmission(source, compiler, "");
	}
	
	/**
	 * Create a new C++ submission with empty input
	 *
	 * @param {string} source - source code
	 * @return API response
	 */
	public JsonObject createSubmission(String source)
	{
		return createSubmission(source, 1, "");
	}

	/**
	 * Create an empty C++ submission with empty input
	 *
	 * @param {string} source - source code
	 * @return API response
	 */
	public JsonObject createSubmission()
	{
		return createSubmission("", 1, "");
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
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id)
	{
		return getSubmission(id, false, false, false, false, false);
	}
}
