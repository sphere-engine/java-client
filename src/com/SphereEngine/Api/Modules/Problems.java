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
	 * Update active testcases related to the problem
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer[]} activeTestcases - Active testcases
	 * @return API response
	 */
	public JsonObject updateProblemActiveTestcases(String problemCode, Integer[] activeTestcases)
	{
		return updateProblem(problemCode, null, null, null, null, null, activeTestcases);
	}
	
	/**
	 * Retrieve list of Problem testcases
	 *
	 * @param {string} problemCode - Problem code
	 * @return API response
	 */
	public JsonObject getProblemTestcases(String problemCode)
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
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output, Double timelimit, Integer judgeId, Boolean active)
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
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output, Double timelimit, Integer judgeId)
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
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output, Double timelimit)
	{
		return createProblemTestcase(problemCode, input, output, timelimit, 1, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1) and timelimit 1 second
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @param {string} output - output data
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input, String output)
	{
		return createProblemTestcase(problemCode, input, output, 1.0, 1, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1), 
	 * timelimit 1 second and empty model output
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} input - input data
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode, String input)
	{
		return createProblemTestcase(problemCode, input, "", 1.0, 1, true);
	}
	
	/**
	 * Create an active problem testcase with Ignore extra whitespaces judge (judgeId = 1), 
	 * timelimit 1 second, empty model output and empty model input 
	 *
	 * @param {string} problemCode - Problem code
	 * @return API response
	 */
	public JsonObject createProblemTestcase(String problemCode)
	{
		return createProblemTestcase(problemCode, "", "", 1.0, 1, true);
	}
	
	/**
	 * Retrieve problem testcase
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @return API response
	 */
	public JsonObject getProblemTestcase(String problemCode, Integer number)
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
	 * @return API response
	 */
	public JsonObject updateProblemTestcase(String problemCode, Integer number, String input, String output, Double timelimit, Integer judgeId)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		Map<String, String> postParams = new HashMap<String,String>();
		
		urlParams.put("problemCode", problemCode);
		urlParams.put("number", number.toString());
		
		if (input != null) postParams.put("input", input);
		if (output != null) postParams.put("output", output);
		if (timelimit != null) postParams.put("timelimit", timelimit.toString());
		if (judgeId != null) postParams.put("judgeId", judgeId.toString());
		 
		return apiClient.callApi("/problems/{problemCode}/testcases/{number}", "PUT", urlParams, null, postParams, null, null);
	}
	
	/**
	 * Retrieve Problem testcase file
	 *
	 * @param {string} problemCode - Problem code
	 * @param {integer} number - Testcase number
	 * @param {string} filename - stream name, enum: input|output
	 * @return file content
	 */
	public String getProblemTestcaseFile(String problemCode, Integer number, String filename)
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
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source, Integer compiler, Integer user)
	{
		Map<String, String> postParams = new HashMap<String,String>();
		
		postParams.put("problemCode", problemCode);
		postParams.put("compilerId", compiler.toString());
		postParams.put("source", source);
		if (user != null) postParams.put("userId", user.toString());
		
		return apiClient.callApi("/submissions", "POST", null, null, postParams, null, null);
	}
	
	/**
	 * Create a new submission with default user
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} source - source code
	 * @param {integer} compiler - Compiler ID
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source, Integer compiler)
	{
		return createSubmission(problemCode, source, compiler, null);
	}
	
	/**
	 * Create a new C++ submission with default user
	 *
	 * @param {string} problemCode - Problem code
	 * @param {string} source - source code
	 * @return API response
	 */
	public JsonObject createSubmission(String problemCode, String source)
	{
		return createSubmission(problemCode, source, 1, null);
	}
	
	/**
	 * Fetch submission details
	 *
	 * @param {integer} id - Submission ID
	 * @return API response
	 */
	public JsonObject getSubmission(Integer id)
	{
		Map<String, String> urlParams = new HashMap<String,String>();
		
		urlParams.put("id", id.toString());
		
		return apiClient.callApi("/submissions/{id}", "GET", urlParams, null, null, null, null);
	}
}
