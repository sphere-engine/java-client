package com.SphereEngine.Api.Modules;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonObject;

import com.SphereEngine.Api.ApiClient;

/*
 * SphereEngine.Api.Modules.Compilers
 * 
 * 0.1
 *
 * 18.01.2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class Compilers
{
	/**
	 * API Client
	 * @param SphereEngine.Api.ApiClient instance of the ApiClient
	 */
	private ApiClient apiClient;
	
	/**
	 * Constructor
	 * @param string accessToken Access token to Sphere Engine service
	 * @param string version version of the API
	 * @param string endpoint link to the endpoint
	 */
	public Compilers(String accessToken, String version, String endpoint)
	{
		apiClient = new ApiClient(accessToken, createEndpointLink(version, endpoint));
	}
	
	private String createEndpointLink(String version, String endpoint)
	{
	    return endpoint + ".api.compilers.sphere-engine.com/api/" + version;
	}
	
	/**
	 * test
	 *
	 * Test method
	 *
	 * @return JsonObject
	 */
	public JsonObject test()
	{
	    return apiClient.callApi("/test", "GET", null, null, null, null, null);
	}
	
	/**
	 * compilers
	 *
	 * List of all compilers
	 *
	 * @return JsonObject
	 */
	public JsonObject getCompilers()
	{
	    return apiClient.callApi("/languages", "GET", null, null, null, null, null);
	}

	/**
	 * create
	 *
	 * Create a new submission
	 *
	 * @param string source source code, default: empty (optional)
	 * @param int compiler Compiler ID, default: 1 (C++) (optional)
	 * @param string input data that will be given to the program on stdin, default: empty (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject createSubmission(String source, String compiler, String input)
	{
		Map<String, String> postParams = new HashMap<String,String>();
		postParams.put("sourceCode", source);
		postParams.put("language", compiler);
		postParams.put("input", input);

		return apiClient.callApi("/submissions", "POST", null, null, postParams, null, null);
	}
	
	/**
	 * get
	 *
	 * Fetch submission details
	 *
	 * @param int id Submission id (required)
	 * @param bool withSource determines whether source code of the submission should be returned, default: false (optional)
	 * @param bool withInput determines whether input data of the submission should be returned, default: false (optional)
	 * @param bool withOutput determines whether output produced by the program should be returned, default: false (optional)
	 * @param bool withStderr determines whether stderr should be returned, default: false (optional)
	 * @param bool withCmpinfo determines whether compilation information should be returned, default: false (optional)
	 * @return JsonObject
	 */
	public JsonObject getSubmission(String id, String withSource, String withInput, String withOutput, String withStderr, String withCmpinfo)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> queryParams = new HashMap<String,String>();
		
		urlParams.put("id", id);
		
		queryParams.put("withSource", withSource);
		queryParams.put("withInput", withInput);
		queryParams.put("withOutput", withOutput);
		queryParams.put("withStderr", withStderr);
		queryParams.put("withCmpinfo", withCmpinfo);

		return apiClient.callApi("/submissions/{id}", "GET", urlParams, queryParams, null, null, null);
	}
}
