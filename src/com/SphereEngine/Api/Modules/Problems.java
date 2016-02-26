package com.SphereEngine.Api.Modules;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import com.google.gson.JsonObject;

import com.SphereEngine.Api.ApiClient;
import com.SphereEngine.Api.Settings;

/*
 * SphereEngine.Api.Modules.Problems
 * 
 * 0.1
 *
 * 18.01.2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class Problems
{
	/**
     * API Client
     * @param SphereEngine.Api.ApiClient instance of the ApiClient
     */
	private ApiClient apiClient;
	
	/**
	 * Constructor
	 * 
	 * @param {Settings} settings - API settings object
	 */
	public Problems(Settings settings)
	{
		String accessToken = settings.getProblemsAccessToken();
		String version = settings.getProblemsVersion();
		String endpoint = settings.getProblemsEndpoint();
		
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
			return "problems.sphere-engine.com/api/" + version;
		} else {
			return endpoint + "/api/" + version;
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
	    return apiClient.callApi("/compilers", "GET", null, null, null, null, null);
	}

	/**
	 * List of judges
	 *
	 * @param {integer} limit - limit of judges to get
	 * @param {integer} offset  - offset
	 * @param {string} type - Judge type, enum: testcase|master
	 * @return API response
	 */
	public JsonObject getJudges(Integer limit, Integer offset, String type)
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
	 * @return API response
	 */
	public JsonObject getJudges(Integer limit, Integer offset)
	{
		return getJudges(limit, offset, "testcase");
	}
	
	/**
	 * List of testcase judges starting from the first one
	 *
	 * @param {integer} limit - limit of judges to get
	 * @return API response
	 */
	public JsonObject getJudges(Integer limit)
	{
		return getJudges(limit, 0, "testcase");
	}
	
	/**
	 * getJudges
	 *
	 * List of first 10 testcase judges starting from the first one
	 * 
	 * @return API response
	 */
	public JsonObject getJudges()
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
	 * @return API response
	 */
	public JsonObject createJudge(String source, Integer compiler, String type, String name)
	{
		Map<String, String> postParams = new HashMap<String,String>();

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
	 * @return API response
	 */
	public JsonObject createJudge(String source, Integer compiler, String type)
	{
		return createJudge(source, compiler, type, "");
	}
	
	/**
	 * Create a new testcase judge with empty name
	 *
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @return API response
	 */
	public JsonObject createJudge(String source, Integer compiler)
	{
		return createJudge(source, compiler, "testcase", "");
	}

	/**
	 * Create a new C++ testcase judge with empty name
	 *
	 * @param {string} source - source code
	 * @return API response
	 */
	public JsonObject createJudge(String source)
	{
		return createJudge(source, 1, "testcase", "");
	}
	
	/**
	 * Get judge details
	 *
	 * @param {integer} id - Judge ID
	 * @return API response
	 */
	public JsonObject getJudge(Integer id)
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
	 * @return API response
	 */
	public JsonObject updateJudge(Integer id, String source, Integer compiler, String name)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		urlParams.put("id", Integer.toString(id));

		if (source != null) postParams.put("source", source);
		if (compiler != null) postParams.put("compilerId", compiler.toString());
		if (name != null) postParams.put("name", name);
		 
		return apiClient.callApi("/judges/{id}", "PUT", urlParams, null, postParams, null, null);
	}
	
	/**
	 * List of problems
	 *
	 * @param {integer} limit - limit of problems to get
	 * @param {integer} offset - offset
	 * @return API response
	 */
	public JsonObject getProblems(Integer limit, Integer offset)
	{
		Map<String, String> queryParams = new HashMap<String,String>();
		
		queryParams.put("limit", limit.toString());
		queryParams.put("offset", offset.toString());
		
		return apiClient.callApi("/problems", "GET", null, queryParams, null, null, null);
	}
	
	/**
	 * List of problems starting from the first one
	 *
	 * @param {integer} limit - limit of problems to get
	 * @return API response
	 */
	public JsonObject getProblems(Integer limit)
	{
		return getProblems(limit, 0);
	}
	
	/**
	 * List of 10 problems starting from the first one
	 *
	 * @return API response
	 */
	public JsonObject getProblems()
	{
		return getProblems(10, 0);
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
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body, String type, Boolean interactive, Integer masterjudge)
	{
		Map<String, String> postParams = new HashMap<String,String>();
		
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
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body, String type, Boolean interactive)
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
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body, String type)
	{
		return createProblem(code, name, body, type, false, 1001);
	}
	
	/**
	 * Create a new not interactive binary problem for masterjudge = 1001
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @param {string} body - Problem body
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name, String body)
	{
		return createProblem(code, name, body, "binary", false, 1001);
	}
	
	/**
	 * Create a new not interactive binary empty problem for masterjudge = 1001
	 *
	 * @param {string} code - Problem code
	 * @param {string} name - Problem name
	 * @return API response
	 */
	public JsonObject createProblem(String code, String name)
	{
		return createProblem(code, name, "", "binary", false, 1001);
	}
	
	/**
	 * Retrieve an existing problem
	 *
	 * @param {string} code - Problem code
	 * @return API response
	 */
	public JsonObject getProblem(String code)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("code", code);

		return apiClient.callApi("/problems/{code}", "GET", urlParams, null, null, null, null);
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
	 * @return API response
	 */
	public JsonObject updateProblem(String code, String name, String body, String type, Boolean interactive, Integer masterjudge, Integer[] activeTestcases)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		urlParams.put("code", code);

		
		if (name != null) postParams.put("name", name);
		
		if (name != null) postParams.put("name", name);
		if (body != null) postParams.put("body", body);
		if (type != null) postParams.put("type", type);
		if (interactive != null) postParams.put("interactive", (interactive) ? "1" : "0");
		if (masterjudge != null) postParams.put("masterjudgeId", masterjudge.toString());
		if (activeTestcases != null) postParams.put("activeTestcases", 
				Arrays.toString(activeTestcases).split("[\\[\\]]")[1].replace(" ", ""));
	
		return apiClient.callApi("/problems/{code}", "PUT", urlParams, null, postParams, null, null);
	}
}
//	
//	/**
//	 * updateActiveTestcases
//	 *
//	 * Update active testcases related to the problem
//	 *
//	 * @param string problemCode Problem code (required)
//	 * @param int[] activeTestcases Active testcases (required)
//	 * @return void
//	 */
//	public JsonObject updateProblemActiveTestcases(problemCode, activeTestcases)
//	{
//		return update(problemCode, null, null, null, null, null, activeTestcases);
//	}
//	
//	/**
//	 * allTestcases
//	 *
//	 * Retrieve list of Problem testcases
//	 *
//	 * @param string problemCode Problem code (required)
//	 * 
//	 * @return JsonObject
//	 */
//	public JsonObject getProblemTestcases(problemCode)
//	{
//		urlParams = [
//				'problemCode' => problemCode
//		];
//		return apiClient.callApi('/problems/{problemCode}/testcases', 'GET', urlParams, null, null, null);
//	}
//	
//	/**
//	 * createTestcase
//	 *
//	 * Create a problem testcase
//	 *
//	 * @param string problemCode Problem code (required)
//	 * @param string input input data, default: empty (optional)
//	 * @param string output output data, default: empty (optional)
//	 * @param float timelimit time limit in seconds, default: 1 (optional)
//	 * @param int judgeId Judge ID, default: 1 (Ignore extra whitespaces) (optional)
//	 * @param bool active if test should be active, default: true (optional)
//	 * 
//	 * @return JsonObject
//	 */
//	public JsonObject createProblemTestcase(problemCode, input="", output="", timelimit=1, judgeId=1, active=true)
//	{
//		urlParams = [
//				'problemCode' => problemCode
//		];
//		postParams = [
//				'input' => input,
//				'output' => output,
//				'timelimit' => timelimit,
//				'judgeId' => judgeId,
//				'active' => active
//		];
//		return apiClient.callApi('/problems/{problemCode}/testcases', 'POST', urlParams, null, postParams, null);
//	}
//	
//	/**
//	 * getTestcase
//	 *
//	 * Retrieve problem testcase
//	 *
//	 * @param string problemCode Problem code (required)
//	 * @param int number Testcase number (required)
//	 * 
//	 * @return JsonObject
//	 */
//	public JsonObject getProblemTestcase(problemCode, number)
//	{
//		urlParams = [
//				'problemCode' => problemCode,
//				'number' => number
//		];
//		return apiClient.callApi('/problems/{problemCode}/testcases/{number}', 'GET', urlParams, null, null, null);
//	}
//	
//	/**
//	 * updateTestcase
//	 *
//	 * Update the problem testcase
//	 *
//	 * @param string problemCode Problem code (required)
//	 * @param int number Testcase number (required)
//	 * @param string input input data (optional)
//	 * @param string output output data (optional)
//	 * @param float timelimit time limit in seconds (optional)
//	 * @param int judgeId Judge ID (optional)
//	 * 
//	 * @return void
//	 */
//	public JsonObject updateProblemTestcase(problemCode, number, input=null, output=null, timelimit=null, judgeId=null)
//	{
//		urlParams = [
//				'problemCode' => problemCode,
//				'number' => number
//		];
//		postParams = [];
//		if (isset(input)) postParams['input'] = input;
//		if (isset(output)) postParams['output'] = output;
//		if (isset(timelimit)) postParams['timelimit'] = timelimit;
//		if (isset(judgeId)) postParams['judgeId'] = judgeId;
//		 
//		return apiClient.callApi('/problems/{problemCode}/testcases/{number}', 'PUT', urlParams, null, postParams, null);
//	}
//	
//	/**
//	 * getTestcaseFile
//	 *
//	 * Retrieve Problem testcase file
//	 *
//	 * @param string problemCode Problem code (required)
//	 * @param int number Testcase number (required)
//	 * @param string filename stream name (required)
//	 * 
//	 * @return file
//	 */
//	public JsonObject getProblemTestcaseFile(problemCode, number, filename)
//	{
//		urlParams = [
//				'problemCode' => problemCode,
//				'number' => number,
//				'filename' => filename
//		];
//		return apiClient.callApi('/problems/{problemCode}/testcases/{number}/{filename}', 'GET', urlParams, null, null, null, 'file');
//	}
//	
//	/**
//	 * create
//	 *
//	 * Create a new submission
//	 *
//	 * @param string problemCode Problem code (required)
//	 * @param string source source code (required)
//	 * @param int compiler Compiler ID (required)
//	 * @param int user User ID, default: account owner user (optional)
//	 * 
//	 * @return JsonObject
//	 */
//	public JsonObject createSubmission(problemCode, source, compiler, user=null)
//	{
//		postParams = [
//				'problemCode' => problemCode,
//				'compilerId' => compiler,
//				'source' => source
//		];
//		if (user != null) postParams['userId'] = user;
//		return apiClient.callApi('/submissions', 'POST', null, null, postParams, null);
//	}
//	
//	/**
//	 * get
//	 *
//	 * Fetch submission details
//	 *
//	 * @param string id Submission ID (required)
//	 * 
//	 * @return JsonObject
//	 */
//	public JsonObject getSubmission(id)
//	{
//		urlParams = [
//				'id' => id
//		];
//		return apiClient.callApi('/submissions/{id}', 'GET', urlParams, null, null, null);
//	}
//}
