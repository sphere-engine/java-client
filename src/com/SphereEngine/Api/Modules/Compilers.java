package com.SphereEngine.Api.Modules;

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
	 * @var SphereEngine.Api.ApiClient instance of the ApiClient
	 */
	private ApiClient apiClient;
	
	/**
	 * Constructor
	 * @param string accessToken Access token to Sphere Engine service
	 * @param string version version of the API
	 * @param string endpoint link to the endpoint
	 */
	public Compilers(accessToken, version, endpoint)
	{
		apiClient = new ApiClient(accessToken, createEndpointLink(version, endpoint));
	}
	
	private String createEndpointLink(version, endpoint)
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
	    return apiClient->callApi('/test', 'GET', null, null, null, null);
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
	    return apiClient->callApi('/languages', 'GET', null, null, null, null);
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
	public JsonObject createSubmission(source="", compiler=1, input="")
	{
		postParams = [
				'sourceCode' => source,
				'language' => compiler,
				'input' => input
		];
		return apiClient->callApi('/submissions', 'POST', null, null, postParams, null);
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
	public JsonObject getSubmission(id, withSource=false, withInput=false, withOutput=false, withStderr=false, withCmpinfo=false)
	{
		urlParams = [
				'id' => id
		];
		queryParams = [
				'withSource' => withSource,
				'withInput' => withInput,
				'withOutput' => withOutput,
				'withStderr' => withStderr,
				'withCmpinfo' => withCmpinfo
		];
		return apiClient->callApi('/submissions/{id}', 'GET', urlParams, queryParams, null, null);
	}
}
