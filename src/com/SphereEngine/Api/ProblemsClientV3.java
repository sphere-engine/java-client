package com.SphereEngine.Api;

import java.util.Map;

import java.util.HashMap;
import java.util.Arrays;

import com.SphereEngine.Api.Exception.ClientException;
import com.SphereEngine.Api.Exception.BadRequestException;
import com.SphereEngine.Api.Exception.ConnectionException;
import com.google.gson.JsonObject;

/*
 * SphereEngine.Api.Modules.Problems
 * 
 * 0.1
 *
 * 18.01.2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class ProblemsClientV3
{
	/**
     * API Client
     * @param SphereEngine.Api.ApiClient instance of the ApiClient
     */
	private ApiClient apiClient;
	
	/**
	 * Module name of the API
	 */
	private String module = "problems";
	
	/**
	 * Version of the API
	 */
	private String version = "v3";
	
	/**
	 * Constructor
	 * 
	 * @param {String} accessToken - Sphere Engine Problems access token
	 * @param {String} endpoint - Sphere Engine Problems endpoint
	 */
	public ProblemsClientV3(String accessToken, String endpoint)
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
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject test() throws ClientException, ConnectionException
	{
		return apiClient.callApi("/test", "GET", null, null, null, null, null);
	}
	
	/**
	 * List of all compilers
	 *
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */	
	public JsonObject getCompilers() throws ClientException, ConnectionException
	{
	    return apiClient.callApi("/compilers", "GET", null, null, null, null, null);
	}

	/**
	 * List of judges
	 *
	 * @param {integer} limit - limit of judges to get
	 * @param {integer} offset  - offset
	 * @param {string} type - Judge type, enum: testcase|master
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getJudges(Integer limit, Integer offset, String type) throws ClientException, ConnectionException
	{
		Map<String, String> queryParams = new HashMap<String,String>();
		
		queryParams.put("limit", Integer.toString(limit));
		queryParams.put("offset", Integer.toString(offset));
		queryParams.put("type", type);

		return apiClient.callApi("/judges", "GET", null, queryParams, null, null, null);
	}

	/**
	 * List of testcase judges
	 *
	 * @param {integer} limit - limit of judges to get
	 * @param {integer} offset - offset
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getJudges(Integer limit, Integer offset) throws ClientException, ConnectionException
	{
		return getJudges(limit, offset, "testcase");
	}
	
	/**
	 * List of testcase judges starting from the first one
	 *
	 * @param {integer} limit - limit of judges to get
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getJudges(Integer limit) throws ClientException, ConnectionException
	{
		return getJudges(limit, 0, "testcase");
	}
	
	/**
	 * List of first 10 testcase judges starting from the first one
	 * 
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getJudges() throws ClientException, ConnectionException
	{
		return getJudges(10, 0, "testcase");
	}
	
	/**
	 * Create a new judge
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {string} type - Judge type, enum: testcase|master
	 * @param {string} name - Judge name
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty source code
	 * @throws NotFoundException for non existing compiler
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createJudge(String source, Integer compiler, String type, String name) throws ClientException, ConnectionException
	{
		Map<String, String> postParams = new HashMap<String,String>();
		
		if ("".equals(source)) {
			throw new BadRequestException("empty source"); 
		}

		postParams.put("source", source);
		postParams.put("compilerId", compiler.toString());
		postParams.put("type", type);
		postParams.put("name", name);

		return apiClient.callApi("/judges", "POST", null, null, postParams, null, null);
	}
	
	/**
	 * Create a new judge with empty name
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {string} type - Judge type, enum: testcase|master
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty source code
	 * @throws NotFoundException for non existing compiler
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createJudge(String source, Integer compiler, String type) throws ClientException, ConnectionException
	{
		return createJudge(source, compiler, type, "");
	}
	
	/**
	 * Create a new testcase judge with empty name
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty source code
	 * @throws NotFoundException for non existing compiler
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createJudge(String source, Integer compiler) throws ClientException, ConnectionException
	{
		return createJudge(source, compiler, "testcase", "");
	}

	/**
	 * Create a new C++ testcase judge with empty name
	 *
	 * @param {string} source - source code
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createJudge(String source) throws ClientException, ConnectionException
	{
		return createJudge(source, 1, "testcase", "");
	}
	
	/**
	 * Get judge details
	 *
	 * @param {integer} id - Judge ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing judge
	 * @throws ForbiddenException for retrieving foreign judge details
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getJudge(Integer id) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("id", Integer.toString(id));
		
		return apiClient.callApi("/judges/{id}", "GET", urlParams, null, null, null, null);
	}
	
	/**
	 * Update judge
	 *
	 * @param {integer} id - Judge ID
	 * @param {string} source - source code (optional, put null if you don't want to update)
	 * @param {integer} compiler - Compiler ID (optional, put null if you don't want to update)
	 * @param {string} name - Judge name (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign judge
	 * @throws NotFoundException for non existing judge
	 * @throws NotFoundException for non existing compiler
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateJudge(Integer id, String source, Integer compiler, String name) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		if ("".equals(source)) {
			throw new BadRequestException("empty source"); 
		}
		
		urlParams.put("id", Integer.toString(id));

		if (source != null) postParams.put("source", source);
		if (compiler != null) postParams.put("compilerId", compiler.toString());
		if (name != null) postParams.put("name", name);
		 
		return apiClient.callApi("/judges/{id}", "PUT", urlParams, null, postParams, null, null);
	}
	
	/**
	 * Update judge (without: name)
	 *
	 * @param {integer} id - Judge ID
	 * @param {string} source - source code (optional, put null if you don't want to update)
	 * @param {integer} compiler - Compiler ID (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign judge
	 * @throws NotFoundException for non existing judge
	 * @throws NotFoundException for non existing compiler
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateJudge(Integer id, String source, Integer compiler) throws ClientException, ConnectionException
	{
		return updateJudge(id, source, compiler, null);
	}
	
	/**
	 * Update judge (without: compiler, name)
	 *
	 * @param {integer} id - Judge ID
	 * @param {string} source - source code (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing judge
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateJudge(Integer id, String source) throws ClientException, ConnectionException
	{
		return updateJudge(id, source, null, null);
	}
	
	/**
	 * List of problems
	 *
	 * @param {integer} limit - limit of problems to get
	 * @param {integer} offset - offset
	 * @param {boolean} shortBody - determines whether shortened body should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblems(Integer limit, Integer offset, Boolean shortBody) throws ClientException, ConnectionException
	{
		Map<String, String> queryParams = new HashMap<String,String>();
		
		queryParams.put("limit", limit.toString());
		queryParams.put("offset", offset.toString());
		queryParams.put("shortBody", (shortBody) ? "1" : "0");
		
		return apiClient.callApi("/problems", "GET", null, queryParams, null, null, null);
	}
	
	/**
	 * List of problems without shortened body
	 *
	 * @param {integer} limit - limit of problems to get
	 * @param {integer} offset - offset
	 * @param {boolean} shortBody - determines whether shortened body should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblems(Integer limit, Integer offset) throws ClientException, ConnectionException
	{
		return getProblems(limit, offset, false);
	}

	/**
	 * List of problems starting from the first one without shortened body
	 *
	 * @param {integer} limit - limit of problems to get
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblems(Integer limit) throws ClientException, ConnectionException
	{
		return getProblems(limit, 0, false);
	}
	
	/**
	 * List of 10 problems starting from the first one without shortened body
	 *
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblems() throws ClientException, ConnectionException
	{
		return getProblems(10, 0, false);
	}
	
	/**
	 * Create a new problem
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @param {string} body - Problem body
	 * @param {string} type - Problem type, enum: binary|min|max
	 * @param {boolean} interactive - interactive problem flag
	 * @param {integer} masterjudge - Masterjudge ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws BadRequestException for not unique problem code
	 * @throws BadRequestException for invalid problem code
	 * @throws NotFoundException for non existing masterjudge
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body, String type, Boolean interactive, Integer masterjudge) throws ClientException, ConnectionException
	{
		Map<String, String> postParams = new HashMap<String,String>();
		
		if ("".equals(code)) {
			throw new BadRequestException("empty code");
		} else if ("".equals(name)) {
			throw new BadRequestException("empty name");
		}
		
		postParams.put("code", code);
		postParams.put("name", name);
		postParams.put("body", body);
		postParams.put("type", type);
		postParams.put("interactive", (interactive) ? "1" : "0");
		postParams.put("masterjudgeId", masterjudge.toString());
		
		return apiClient.callApi("/problems", "POST", null, null, postParams, null, null);
	}
	
	/**
	 * Create a new problem for masterjudge = 1001
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @param {string} body - Problem body
	 * @param {string} type - Problem type, enum: binary|min|max
	 * @param {boolean} interactive - interactive problem flag
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws BadRequestException for not unique problem code
	 * @throws BadRequestException for invalid problem code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body, String type, Boolean interactive) throws ClientException, ConnectionException
	{
		return createProblem(code, name, body, type, interactive, 1001);
	}
	
	/**
	 * Create a new not interactive problem for masterjudge = 1001
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @param {string} body - Problem body
	 * @param {string} type - Problem type, enum: binary|min|max
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws BadRequestException for not unique problem code
	 * @throws BadRequestException for invalid problem code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body, String type) throws ClientException, ConnectionException
	{
		return createProblem(code, name, body, type, false, 1001);
	}
	
	/**
	 * Create a new not interactive binary problem for masterjudge = 1001
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @param {string} body - Problem body
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws BadRequestException for not unique problem code
	 * @throws BadRequestException for invalid problem code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body) throws ClientException, ConnectionException
	{
		return createProblem(code, name, body, "binary", false, 1001);
	}
	
	/**
	 * Create a new not interactive binary empty problem for masterjudge = 1001
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @throws NotAuthorizedException for invalid access token
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws BadRequestException for not unique problem code
	 * @throws BadRequestException for invalid problem code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name) throws ClientException, ConnectionException
	{
		return createProblem(code, name, "", "binary", false, 1001);
	}
	
	/**
	 * Retrieve an existing problem
	 *
	 * @param {string} code - Problem code
	 * @param {boolean} shortBody - determines whether shortened body should be returned
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblem(String code, Boolean shortBody) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> queryParams = new HashMap<String,String>();
				
		urlParams.put("code", code);
		queryParams.put("shortBody", (shortBody) ? "1" : "0");
		
		return apiClient.callApi("/problems/{code}", "GET", urlParams, queryParams, null, null, null);
	}
	
	/**
	 * Retrieve an existing problem wihtout shortened body
	 *
	 * @param {string} code - Problem code
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblem(String code) throws ClientException, ConnectionException
	{
		return getProblem(code, false);
	}

	/**
	 * Update an existing problem
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name (optional, put null if you don't want to update)
	 * @param {string} body - Problem body (optional, put null if you don't want to update)
	 * @param {string} type - Problem type, enum: binary|min|max (optional, put null if you don't want to update)
	 * @param {boolean} interactive - interactive problem flag (optional, put null if you don't want to update)
	 * @param {integer} masterjudge - Masterjudge ID (optional, put null if you don't want to update)
	 * @param {integer[]} activeTestcases list of active testcases IDs (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing masterjudge
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name, String body, String type, Boolean interactive, Integer masterjudge, Integer[] activeTestcases) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		if ("".equals(code)) {
			throw new BadRequestException("empty code");
		} else if ("".equals(name)) {
			throw new BadRequestException("empty name");
		}
		
		urlParams.put("code", code);
		
		if (name != null) postParams.put("name", name);
		if (body != null) postParams.put("body", body);
		if (type != null) postParams.put("type", type);
		if (interactive != null) postParams.put("interactive", (interactive) ? "1" : "0");
		if (masterjudge != null) postParams.put("masterjudgeId", masterjudge.toString());
		if (activeTestcases != null) {
			if (activeTestcases.length > 0) {
				postParams.put("activeTestcases", 
						Arrays.toString(activeTestcases).split("[\\[\\]]")[1].replace(" ", ""));
			} else {
				postParams.put("activeTestcases", "");
			}
		}
		
		return apiClient.callApi("/problems/{code}", "PUT", urlParams, null, postParams, null, null);
	}
	
	/**
	 * Update an existing problem (without: activeTestcases)
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name (optional, put null if you don't want to update)
	 * @param {string} body - Problem body (optional, put null if you don't want to update)
	 * @param {string} type - Problem type, enum: binary|min|max (optional, put null if you don't want to update)
	 * @param {boolean} interactive - interactive problem flag (optional, put null if you don't want to update)
	 * @param {integer} masterjudge - Masterjudge ID (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing masterjudge
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name, String body, String type, Boolean interactive, Integer masterjudge) throws ClientException, ConnectionException
	{
		return updateProblem(code, name, body, type, interactive, masterjudge, null);
	}
	
	/**
	 * Update an existing problem (without: masterjudge, activeTestcases)
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name (optional, put null if you don't want to update)
	 * @param {string} body - Problem body (optional, put null if you don't want to update)
	 * @param {string} type - Problem type, enum: binary|min|max (optional, put null if you don't want to update)
	 * @param {boolean} interactive - interactive problem flag (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name, String body, String type, Boolean interactive) throws ClientException, ConnectionException
	{
		return updateProblem(code, name, body, type, interactive, null, null);
	}
	
	/**
	 * Update an existing problem (without: interactive, masterjudge, activeTestcases)
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name (optional, put null if you don't want to update)
	 * @param {string} body - Problem body (optional, put null if you don't want to update)
	 * @param {string} type - Problem type, enum: binary|min|max (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name, String body, String type) throws ClientException, ConnectionException
	{
		return updateProblem(code, name, body, type, null, null, null);
	}
	
	/**
	 * Update an existing problem (without: type, interactive, masterjudge, activeTestcases)
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name (optional, put null if you don't want to update)
	 * @param {string} body - Problem body (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name, String body) throws ClientException, ConnectionException
	{
		return updateProblem(code, name, body, null, null, null, null);
	}
	
	/**
	 * Update an existing problem (without: type, interactive, masterjudge, activeTestcases)
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name (optional, put null if you don't want to update)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws BadRequestException for empty problem code
	 * @throws BadRequestException for empty problem name
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name) throws ClientException, ConnectionException
	{
		return updateProblem(code, name, null, null, null, null, null);
	}
	
	/**
	 * Update active testcases related to the problem
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer[]} activeTestcases - Active testcases
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for modifying foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws BadRequestException for empty problem code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblemActiveTestcases(String problemCode, Integer[] activeTestcases) throws ClientException, ConnectionException
	{
		return updateProblem(problemCode, null, null, null, null, null, activeTestcases);
	}
	
	/**
	 * Retrieve list of Problem testcases
	 *
	 * @param {string} problemCode - Problem code
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for retrieving testcases of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblemTestcases(String problemCode) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		
		return apiClient.callApi("/problems/{problemCode}/testcases", "GET", urlParams, null, null, null, null);
	}
	
	/**
	 * Create a problem testcase
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @param {double} timelimit - time limit in seconds
	 * @param {integer} judgeId - Judge ID
	 * @param {boolean} active - activate test
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for adding a testcase to foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing judge
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output, Double timelimit, Integer judgeId, Boolean active) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		
		postParams.put("input", input);
		postParams.put("output", output);
		postParams.put("timelimit", timelimit.toString());
		postParams.put("judgeId", judgeId.toString());
		postParams.put("active",  (active) ? "1" : "0");

		return apiClient.callApi("/problems/{problemCode}/testcases", "POST", urlParams, null, postParams, null, null);
	}
	
	/**
	 * Create an active problem testcase
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @param {double} timelimit - time limit in seconds
	 * @param {integer} judgeId - Judge ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for adding a testcase to foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing judge
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output, Double timelimit, Integer judgeId) throws ClientException, ConnectionException
	{
		return createProblemTestcase(problemCode, input, output, timelimit, judgeId, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1)
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @param {double} timelimit - time limit in seconds
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for adding a testcase to foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output, Double timelimit) throws ClientException, ConnectionException
	{
		return createProblemTestcase(problemCode, input, output, timelimit, 1, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1) and timelimit 1 second
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for adding a testcase to foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output) throws ClientException, ConnectionException
	{
		return createProblemTestcase(problemCode, input, output, 1.0, 1, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1), 
	 * timelimit 1 second and empty model output
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for adding a testcase to foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input) throws ClientException, ConnectionException
	{
		return createProblemTestcase(problemCode, input, "", 1.0, 1, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1), 
	 * timelimit 1 second, empty model output and empty model input 
	 *
	 * @param {string} problemCode - Problem code
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for adding a testcase to foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode) throws ClientException, ConnectionException
	{
		return createProblemTestcase(problemCode, "", "", 1.0, 1, true);
	}
	
	/**
	 * Retrieve problem testcase
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for retrieving a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getProblemTestcase(String problemCode, Integer number) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		urlParams.put("number", number.toString());
		
		return apiClient.callApi("/problems/{problemCode}/testcases/{number}", "GET", urlParams, null, null, null, null);
	}
	
	/**
	 * Update the problem testcase
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @param {double} timelimit - time limit in seconds
	 * @param {integer} judgeId - Judge ID
	 * @param {boolean} active - activate test
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for updating a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws NotFoundException for non existing judge
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblemTestcase(String problemCode, Integer number, String input, String output, Double timelimit, Integer judgeId, Boolean active) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		urlParams.put("number", number.toString());
		
		if (input != null) postParams.put("input", input);
		if (output != null) postParams.put("output", output);
		if (timelimit != null) postParams.put("timelimit", timelimit.toString());
		if (judgeId != null) postParams.put("judgeId", judgeId.toString());
		if (active != null) postParams.put("active",  (active) ? "1" : "0");
		 
		return apiClient.callApi("/problems/{problemCode}/testcases/{number}", "PUT", urlParams, null, postParams, null, null);
	}
	
	/**
	 * Update the problem testcase (without: active)
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @param {double} timelimit - time limit in seconds
	 * @param {integer} judgeId - Judge ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for updating a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws NotFoundException for non existing judge
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblemTestcase(String problemCode, Integer number, String input, String output, Double timelimit, Integer judgeId) throws ClientException, ConnectionException
	{
		return updateProblemTestcase(problemCode, number, input, output, timelimit, judgeId, null);
	}
	
	/**
	 * Update the problem testcase (without: judgeId, active)
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @param {double} timelimit - time limit in seconds
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for updating a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblemTestcase(String problemCode, Integer number, String input, String output, Double timelimit) throws ClientException, ConnectionException
	{
		return updateProblemTestcase(problemCode, number, input, output, timelimit, null, null);
	}
	
	/**
	 * Update the problem testcase (without: timelimit, judgeId, active)
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for updating a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblemTestcase(String problemCode, Integer number, String input, String output) throws ClientException, ConnectionException
	{
		return updateProblemTestcase(problemCode, number, input, output, null, null, null);
	}
	
	/**
	 * Update the problem testcase (without: output, timelimit, judgeId, active)
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} input - input data
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for updating a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject updateProblemTestcase(String problemCode, Integer number, String input) throws ClientException, ConnectionException
	{
		return updateProblemTestcase(problemCode, number, input, null, null, null, null);
	}
	
	/**
	 * Delete the problem testcase
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for removing a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject deleteProblemTestcase(String problemCode, Integer number) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		urlParams.put("number", number.toString());
		 
		return apiClient.callApi("/problems/{problemCode}/testcases/{number}", "DELETE", urlParams, null, null, null, null);
	}
	
	/**
	 * Retrieve Problem testcase file
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} filename - stream name, enum: input|output
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ForbiddenException for retrieving a file of a testcase of foreign problem
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing testcase
	 * @throws NotFoundException for non existing file
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return file content
	 */
	public String getProblemTestcaseFile(String problemCode, Integer number, String filename) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		urlParams.put("number", number.toString());
		urlParams.put("filename", filename);
		
		return apiClient.
				callApi("/problems/{problemCode}/testcases/{number}/{filename}", "GET", urlParams, null, null, null, "file")
				.get("file_content")
				.getAsString();
	}
	
	/**
	 * Create a new submission
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {integer} user - User ID
	 * @param {integer} priority - priority of the submission
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing compiler
	 * @throws NotFoundException for non existing user
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source, Integer compiler, Integer user, Integer priority) throws ClientException, ConnectionException
	{
		Map<String, String> postParams = new HashMap<String,String>();
		
		if ("".equals(source)) {
			throw new BadRequestException("empty source");
		}
		
		postParams.put("problemCode", problemCode);
		postParams.put("compilerId", compiler.toString());
		postParams.put("source", source);
		if (user != null) postParams.put("userId", user.toString());
		if (priority != null) postParams.put("priority", priority.toString());
		
		return apiClient.callApi("/submissions", "POST", null, null, postParams, null, null);
	}
	
	/**
	 * Create a new submission with normal priority
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @param {integer} user - User ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing compiler
	 * @throws NotFoundException for non existing user
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source, Integer compiler, Integer user) throws ClientException, ConnectionException
	{
		return createSubmission(problemCode, source, compiler, user, null);
	}
	
	/**
	 * Create a new submission with normal priority and default user
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing problem
	 * @throws NotFoundException for non existing compiler
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source, Integer compiler) throws ClientException, ConnectionException
	{
		return createSubmission(problemCode, source, compiler, null, null);
	}
	
	/**
	 * Create a new C++ submission with normal priority and default user
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} source - source code
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing problem
	 * @throws BadRequestException for empty source code
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source) throws ClientException, ConnectionException
	{
		return createSubmission(problemCode, source, 1, null, null);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission ID
	 * @throws NotAuthorizedException for invalid access token
	 * @throws NotFoundException for non existing submission
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id) throws ClientException, ConnectionException
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("id", id.toString());
		
		return apiClient.callApi("/submissions/{id}", "GET", urlParams, null, null, null, null);
	}
	
	/**
	 * Fetches status of multiple submissions (maximum 20 ids)
	 *
	 * @param {Integer[]} ids - Submission ids (required)
	 * @throws NotAuthorizedException for invalid access token
	 * @throws ClientException
	 * @throws ConnectionException
	 * @return API response
	 */
	public JsonObject getSubmissions(Integer[] ids) throws ClientException, ConnectionException
	{
		Map<String, String> queryParams = new HashMap<String,String>();
		
		StringBuilder idsStringBuilder = new StringBuilder();
		for(Integer id: ids) {
			if(id == null || id <= 0) {
				continue;
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
}
