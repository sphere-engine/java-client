package com.SphereEngine.Api.Modules;

import com.SphereEngine.Api.ApiClient;

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
     * @var SphereEngine.Api.ApiClient instance of the ApiClient
     */
	private apiClient;
	
    /**
     * Constructor
     * @param string accessToken Access token to Sphere Engine service
     * @param string version version of the API
     * @param string endpoint link to the endpoint
     */
	public Problems(accessToken, version, endpoint)
	{
		apiClient = new ApiClient(accessToken, createEndpointLink(version, endpoint));
	}
	
	private function createEndpointLink(version, endpoint)
	{
	    if(endpoint == null){
	    	return "problems.sphere-engine.com/api/" + version;
	    } else {
	    	return endpoint . "/api/" . version;
	    }
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
	    return apiClient->callApi('/compilers', 'GET', null, null, null, null);
	}
	
	/**
	 * all
	 *
	 * List of all judges
	 *
	 * @param int limit limit of judges to get, default: 10, max: 100 (optional)
	 * @param int offset offset, default: 0 (optional)
	 * @param string type Judge type, enum: testcase|master, default: testcase (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject getJudges(limit=10, offset=0, type="testcase")
	{
		queryParams = [
				'limit' => limit,
				'offset' => offset
		];
		return apiClient->callApi('/judges', 'GET', null, queryParams, null, null);
	}
	
	/**
	 * create
	 *
	 * Create a new judge
	 *
	 * @param string source source code (required)
	 * @param int compiler Compiler ID, default: 1 (C++) (optional)
	 * @param string type Judge type, testcase|master, default: testcase (optional)
	 * @param string name Judge name, default: empty (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject createJudge(source, compiler=1, type="testcase", name="")
	{
		postParams = [
				'source' => source,
				'compilerId' => compiler,
				'type' => type,
				'name' => name,
		];
		return apiClient->callApi('/judges', 'POST', null, null, postParams, null);
	}
	
	/**
	 * get
	 *
	 * Get judge details
	 *
	 * @param int id Judge ID (required)
	 * @return JsonObject
	 */
	public JsonObject getJudge(id)
	{
		urlParams = [
				'id' => id
		];
		return apiClient->callApi('/judges/{id}', 'GET', urlParams, null, null, null);
	}
	
	/**
	 * update
	 *
	 * Update judge
	 *
	 * @param int id Judge ID (required)
	 * @param string source source code (optional)
	 * @param int compiler Compiler ID (optional)
	 * @param string name Judge name (optional)
	 * 
	 * @return void
	 */
	public JsonObject updateJudge(id, source=null, compiler=null, name=null)
	{
		urlParams = [
				'id' => id
		];
		postParams = [];
		if (isset(source)) postParams['source'] = source;
		if (isset(compiler)) postParams['compilerId'] = compiler;
		if (isset(name)) postParams['name'] = name;
		 
		return apiClient->callApi('/judges/{id}', 'PUT', urlParams, null, postParams, null);
	}
	
	/**
	 * all
	 *
	 * List of all problems
	 *
	 * @param int limit limit of problems to get, default: 10, max: 100 (optional)
	 * @param int offset offset, default: 0 (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject getProblems(limit=10, offset=0)
	{
		queryParams = [
				'limit' => limit,
				'offset' => offset
		];
		return apiClient->callApi('/problems', 'GET', null, queryParams, null, null);
	}
	
	/**
	 * create
	 *
	 * Create a new problem
	 *
	 * @param string code Problem code (required)
	 * @param string name Problem name (required)
	 * @param string body Problem body (optional)
	 * @param string type Problem type, enum: binary|min|max, default: binary (optional)
	 * @param bool interactive interactive problem flag, default: 0 (optional)
	 * @param int masterjudge_id Masterjudge ID, default: 1001 (i.e. Score is % of correctly solved testcases) (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject createProblem(code, name, body="", type="binary", interactive=0, masterjudgeId=1001)
	{
		postParams = [
				'code' => code,
				'name' => name,
				'body' => body,
				'type' => type,
				'interactive' => interactive,
				'masterjudgeId' => masterjudgeId
		];
		return apiClient->callApi('/problems', 'POST', null, null, postParams, null);
	}
	
	/**
	 * get
	 *
	 * Retrieve an existing problem
	 *
	 * @param string code Problem code (required)
	 * 
	 * @return JsonObject
	 */
	public JsonObject getProblem(code)
	{
		urlParams = [
				'code' => code
		];
		return apiClient->callApi('/problems/{code}', 'GET', urlParams, null, null, null);
	}
	
	/**
	 * update
	 *
	 * Update an existing problem
	 *
	 * @param string code Problem code (required)
	 * @param string name Problem name (optional)
	 * @param string body Problem body (optional)
	 * @param string type Problem type, enum: binary|min|max, default: binary (optional)
	 * @param bool interactive interactive problem flag (optional)
	 * @param int masterjudgeId Masterjudge ID (optional)
	 * @param int[] activeTestcases list of active testcases IDs (optional)
	 * @return void
	 */
	public JsonObject updateProblem(code, name=null, body=null, type=null, interactive=null, masterjudgeId=null, activeTestcases=null)
	{
		urlParams = [
				'code' => code
		];
		postParams = [];
		if (isset(name)) postParams['name'] = name;
		if (isset(body)) postParams['body'] = body;
		if (isset(type)) postParams['type'] = type;
		if (isset(interactive)) postParams['interactive'] = interactive;
		if (isset(masterjudgeId)) postParams['masterjudgeId'] = masterjudgeId;
		if (isset(activeTestcases) && is_array(activeTestcases)) postParams['activeTestcases'] = implode(',', activeTestcases);
	
		return apiClient->callApi('/problems/{code}', 'PUT', urlParams, null, postParams, null);
	}
	
	/**
	 * updateActiveTestcases
	 *
	 * Update active testcases related to the problem
	 *
	 * @param string problemCode Problem code (required)
	 * @param int[] activeTestcases Active testcases (required)
	 * @return void
	 */
	public JsonObject updateProblemActiveTestcases(problemCode, activeTestcases)
	{
		return update(problemCode, null, null, null, null, null, activeTestcases);
	}
	
	/**
	 * allTestcases
	 *
	 * Retrieve list of Problem testcases
	 *
	 * @param string problemCode Problem code (required)
	 * 
	 * @return JsonObject
	 */
	public JsonObject getProblemTestcases(problemCode)
	{
		urlParams = [
				'problemCode' => problemCode
		];
		return apiClient->callApi('/problems/{problemCode}/testcases', 'GET', urlParams, null, null, null);
	}
	
	/**
	 * createTestcase
	 *
	 * Create a problem testcase
	 *
	 * @param string problemCode Problem code (required)
	 * @param string input input data, default: empty (optional)
	 * @param string output output data, default: empty (optional)
	 * @param float timelimit time limit in seconds, default: 1 (optional)
	 * @param int judgeId Judge ID, default: 1 (Ignore extra whitespaces) (optional)
	 * @param bool active if test should be active, default: true (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject createProblemTestcase(problemCode, input="", output="", timelimit=1, judgeId=1, active=true)
	{
		urlParams = [
				'problemCode' => problemCode
		];
		postParams = [
				'input' => input,
				'output' => output,
				'timelimit' => timelimit,
				'judgeId' => judgeId,
				'active' => active
		];
		return apiClient->callApi('/problems/{problemCode}/testcases', 'POST', urlParams, null, postParams, null);
	}
	
	/**
	 * getTestcase
	 *
	 * Retrieve problem testcase
	 *
	 * @param string problemCode Problem code (required)
	 * @param int number Testcase number (required)
	 * 
	 * @return JsonObject
	 */
	public JsonObject getProblemTestcase(problemCode, number)
	{
		urlParams = [
				'problemCode' => problemCode,
				'number' => number
		];
		return apiClient->callApi('/problems/{problemCode}/testcases/{number}', 'GET', urlParams, null, null, null);
	}
	
	/**
	 * updateTestcase
	 *
	 * Update the problem testcase
	 *
	 * @param string problemCode Problem code (required)
	 * @param int number Testcase number (required)
	 * @param string input input data (optional)
	 * @param string output output data (optional)
	 * @param float timelimit time limit in seconds (optional)
	 * @param int judgeId Judge ID (optional)
	 * 
	 * @return void
	 */
	public JsonObject updateProblemTestcase(problemCode, number, input=null, output=null, timelimit=null, judgeId=null)
	{
		urlParams = [
				'problemCode' => problemCode,
				'number' => number
		];
		postParams = [];
		if (isset(input)) postParams['input'] = input;
		if (isset(output)) postParams['output'] = output;
		if (isset(timelimit)) postParams['timelimit'] = timelimit;
		if (isset(judgeId)) postParams['judgeId'] = judgeId;
		 
		return apiClient->callApi('/problems/{problemCode}/testcases/{number}', 'PUT', urlParams, null, postParams, null);
	}
	
	/**
	 * getTestcaseFile
	 *
	 * Retrieve Problem testcase file
	 *
	 * @param string problemCode Problem code (required)
	 * @param int number Testcase number (required)
	 * @param string filename stream name (required)
	 * 
	 * @return file
	 */
	public JsonObject getProblemTestcaseFile(problemCode, number, filename)
	{
		urlParams = [
				'problemCode' => problemCode,
				'number' => number,
				'filename' => filename
		];
		return apiClient->callApi('/problems/{problemCode}/testcases/{number}/{filename}', 'GET', urlParams, null, null, null, 'file');
	}
	
	/**
	 * create
	 *
	 * Create a new submission
	 *
	 * @param string problemCode Problem code (required)
	 * @param string source source code (required)
	 * @param int compiler Compiler ID (required)
	 * @param int user User ID, default: account owner user (optional)
	 * 
	 * @return JsonObject
	 */
	public JsonObject createSubmission(problemCode, source, compiler, user=null)
	{
		postParams = [
				'problemCode' => problemCode,
				'compilerId' => compiler,
				'source' => source
		];
		if (user != null) postParams['userId'] = user;
		return apiClient->callApi('/submissions', 'POST', null, null, postParams, null);
	}
	
	/**
	 * get
	 *
	 * Fetch submission details
	 *
	 * @param string id Submission ID (required)
	 * 
	 * @return JsonObject
	 */
	public JsonObject getSubmission(id)
	{
		urlParams = [
				'id' => id
		];
		return apiClient->callApi('/submissions/{id}', 'GET', urlParams, null, null, null);
	}
}
