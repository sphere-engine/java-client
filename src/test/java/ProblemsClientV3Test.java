package test.java;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Random;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.SphereEngine.Api.ProblemsClientV3;
import com.google.gson.JsonObject;

public class ProblemsClientV3Test 
{
	static Map<String, String> env;
	static ProblemsClientV3 client;
	
	private String randomNameGenerator()
	{
		Random r = new Random();
		Integer n1 = 1000000 + r.nextInt(8999999);
		Integer n2 = 1000000 + r.nextInt(8999999);
		return n1.toString() + n2.toString();
	}
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@BeforeClass
	static public void setUpBeforeClass()
	{
		env = System.getenv();
		client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
	}
	
	@Test
	public void testAutorizationFail() 
	{
		ProblemsClientV3 badclient = new ProblemsClientV3(
				"wrong access token", 
				env.get("SE_ENDPOINT_PROBLEMS"));
		exception.expect( NotAuthorizedException.class );
		badclient.test();
	}

	@Test
	public void testAutorizationSuccess() 
	{
		client.test();
		assertTrue(true);
	}
	
	@Test
	public void testTestMethodSuccess() 
	{
		assertTrue(client.test().get("message").getAsString().length() > 0);
	}
	
	@Test
	public void testCompilersMethodSuccess()
    {
		assertEquals("C++", 
				client.getCompilers()
					.getAsJsonArray("items")
					.get(0)
					.getAsJsonObject()
					.get("name")
					.getAsString());
    }
	
	@Test
	public void testGetProblemsMethodSuccess()
    {
    	assertEquals(10, 
    			client.getProblems()
    				.getAsJsonObject("paging")
    				.get("limit")
    				.getAsInt());
    	assertEquals(11, 
    			client.getProblems(11)
    				.getAsJsonObject("paging")
    				.get("limit")
    				.getAsInt());
    }
	
	@Test
	public void testGetProblemMethodSuccess()
    {
    	assertEquals("TEST", client.getProblem("TEST").get("code").getAsString());
    }
	
	@Test
	public void testGetProblemMethodWrongCode()
    {
		exception.expect( NotFoundException.class );
    	client.getProblem("NON_EXISTING_PROBLEM");
    }
	
	@Test
    public void testCreateProblemMethodSuccess()
    {
		String r = randomNameGenerator();
		String problem_code = "UT" + r;
		String problem_name = "UT" + r;
		String problem_body = "UT" + r + " body";
		String problem_type = "maximize";
    	Boolean problem_interactive = true;
    	Integer problem_masterjudgeId = 1000;
    	
    	assertEquals(problem_code, client.createProblem(
    			problem_code,
    			problem_name,
    			problem_body,
    			problem_type,
    			problem_interactive,
    			problem_masterjudgeId).get("code").getAsString());
    	
    	JsonObject p = client.getProblem(problem_code);
    	
    	assertEquals("Problem code", problem_code, p.get("code").getAsString());
    	assertEquals("Problem name", problem_name, p.get("name").getAsString());
    	assertEquals("Problem body", problem_body, p.get("body").getAsString());
    	assertEquals("Problem type", problem_type, p.get("type").getAsString());
    	assertEquals("Problem interactive", 1, p.get("interactive").getAsInt());
    	assertEquals("Problem masterjudgeId", problem_masterjudgeId, (Integer) p.getAsJsonObject("masterjudge").get("id").getAsInt());
    }
	
	@Test
	public void testCreateProblemMethodCodeTaken()
    {
		exception.expect( BadRequestException.class );
		client.createProblem("TEST", "Taken problem code");
    }
	
    public void testCreateProblemMethodCodeEmpty()
    {
    	exception.expect( BadRequestException.class );
    	client.createProblem("", "Empty problem code");
    }
    
    @Test
    public void testCreateProblemMethodCodeInvalid()
    {
    	exception.expect( BadRequestException.class );
    	client.createProblem("!@#$%^", "Invalid problem code");
    }
    
    @Test
    public void testCreateProblemMethodEmptyName()
    {
    	exception.expect( BadRequestException.class );
    	client.createProblem("UNIQUE_CODE", "");
    }
    
    @Test
    public void testCreateProblemMethodNonexistingMasterjudge()
    {
    	Integer nonexistingMasterjudgeId = 9999;
    	
    	exception.expect( NotFoundException.class );
    	client.createProblem(
    			"UNIQUE_CODE", 
    			"Nonempty name", 
    			"body", 
    			"binary",
    			false,
    			nonexistingMasterjudgeId);
    }
    
    @Test
    public void testUpdateProblemMethodSuccess()
    {
    	String r = randomNameGenerator();
		String problem_code = "UT" + r;
		String problem_name = "UT" + r;
		client.createProblem(problem_code, problem_name);
    	
		String new_problem_name = problem_name + "updated";
		String new_problem_body = "update";
		String new_problem_type = "maximize";
    	Boolean new_problem_interactive = true;
    	Integer new_problem_masterjudgeId = 1000;
    	
    	client.updateProblem(
    			problem_code,
    			new_problem_name,
    			new_problem_body,
    			new_problem_type,
    			new_problem_interactive,
    			new_problem_masterjudgeId,
    			null);
    	
    	JsonObject p = client.getProblem(problem_code);
    	
    	assertEquals("Problem code", problem_code, p.get("code").getAsString());
    	assertEquals("Problem name", new_problem_name, p.get("name").getAsString());
    	assertEquals("Problem body", new_problem_body, p.get("body").getAsString());
    	assertEquals("Problem type", new_problem_type, p.get("type").getAsString());
    	assertEquals("Problem interactive", 1, p.get("interactive").getAsInt());
    	assertEquals("Problem masterjudgeId", new_problem_masterjudgeId, (Integer) p.getAsJsonObject("masterjudge").get("id").getAsInt());
    }
    
    @Test
    public void testUpdateProblemMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.updateProblem("NON_EXISTING_CODE", "Nonexisting problem code");
    }
    
    @Test
    public void testUpdateProblemMethodNonexistingMasterjudge()
    {
    	Integer nonexistingMasterjudgeId = 9999;
    	
    	exception.expect( NotFoundException.class );
    	client.updateProblem(
    			"TEST", 
    			"Nonempty name",
    			"body",
    			"binary",
    			false,
    			nonexistingMasterjudgeId);
    }
    
    @Test
    public void testUpdateProblemMethodEmptyCode()
    {
    	exception.expect( BadRequestException.class );
    	client.updateProblem("", "Nonempty name");
    }
    
    @Test
    public void testUpdateProblemMethodEmptyName()
    {
    	exception.expect( BadRequestException.class );
    	client.updateProblem("TEST", "");
    }
    
    @Test
    public void testUpdateProblemActiveTestcasesMethodSuccess()
    {
    	client.updateProblemActiveTestcases("TEST", new Integer[0]);
    	assertEquals("", client.getProblem("TEST").get("seq").getAsString());
    	client.updateProblemActiveTestcases("TEST", new Integer[]{0});
    	assertEquals("#0", client.getProblem("TEST").get("seq").getAsString());
    }
    
    @Test
    public void testGetTestcasesMethodSuccess()
    {
    	assertEquals(0, client.getProblemTestcases("TEST").getAsJsonArray("testcases").get(0).getAsJsonObject().get("number").getAsInt());
    }
    
    @Test
    public void testGetProblemTestcasesMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.getProblemTestcases("NON_EXISTING_CODE");
    }
    
    @Test
    public void testGetProblemTestcaseMethodSuccess()
    {
    	assertEquals(0, client.getProblemTestcase("TEST", 0).get("number").getAsInt());
    }
    
    @Test
    public void testGetProblemTestcaseMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.getProblemTestcase("NON_EXISTING_CODE", 0);
    }
    
    @Test
    public void testGetProblemTestcaseMethodNonexistingTestcase()
    {
    	exception.expect( NotFoundException.class );
    	client.getProblemTestcase("TEST", 1);
    }
    
    @Test
    public void testCreateProblemTestcaseMethodSuccess()
    {
    	String r = randomNameGenerator();
    	// create problem to create testcases
    	String problem_code = "UT" + r;
    	String problem_name = "UT" + r;
    	client.createProblem(problem_code, problem_name);
    	 
    	client.createProblemTestcase(problem_code, "in0", "out0", 10.0, 2, false);
    	JsonObject ptc = client.getProblemTestcase(problem_code, 0);
    	
    	assertEquals("Testcase number", 0, ptc.get("number").getAsInt());
    	assertEquals("Testcase active", false, ptc.get("active").getAsBoolean());
    	assertEquals("Testcase timelimit", 10, ptc.getAsJsonObject("limits").get("time").getAsInt());
    	assertEquals("Testcase input size", 3, ptc.getAsJsonObject("input").get("size").getAsInt());
    	assertEquals("Testcase output size", 4, ptc.getAsJsonObject("output").get("size").getAsInt());
    	assertEquals("Testcase judge", 2, ptc.getAsJsonObject("judge").get("id").getAsInt());
    }
    
    @Test
    public void testCreateProblemTestcaseMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.createProblemTestcase("NON_EXISTING_CODE", "in0", "out0", 10.0, 2, true);
    }
    
    @Test
    public void testCreateProblemTestcaseMethodNonexistingJudge()
    {
    	Integer nonexistingJudge = 9999;
    	
    	exception.expect( NotFoundException.class );
    	client.createProblemTestcase("TEST", "in0", "out0", 10.0, nonexistingJudge, true);
    }
    
    @Test
    public void testUpdateProblemTestcaseMethodSuccess()
    {
    	String r = randomNameGenerator();
    	// create problem and testcase to update the testcase
    	String problem_code = "UT" + r;
    	String problem_name = "UT" + r;
    	client.createProblem(problem_code, problem_name);
    	client.createProblemTestcase(problem_code, "in0", "out0", 1.0, 1, true);
    	
    	String new_testcase_input = "in0updated";
    	String new_testcase_output = "out0updated";
    	Double new_testcase_timelimit = 10.0;
    	Integer new_testcase_judge = 2;
    	Boolean new_testcase_active = false;
    	
    	client.updateProblemTestcase(
    			problem_code,
    			0,
    			new_testcase_input, 
    			new_testcase_output, 
    			new_testcase_timelimit, 
    			new_testcase_judge, 
    			new_testcase_active);
    	
    	JsonObject ptc = client.getProblemTestcase(problem_code, 0);
    	
    	assertEquals("Testcase number", 0, ptc.get("number").getAsInt());
    	assertEquals("Testcase active", new_testcase_active, ptc.get("active").getAsBoolean());
    	assertEquals("Testcase timelimit", new_testcase_timelimit, (Double) ptc.getAsJsonObject("limits").get("time").getAsDouble());
    	assertEquals("Testcase input size", new_testcase_input.length(), ptc.getAsJsonObject("input").get("size").getAsInt());
    	assertEquals("Testcase output size", new_testcase_output.length(), ptc.getAsJsonObject("output").get("size").getAsInt());
    	assertEquals("Testcase judge", new_testcase_judge, (Integer) ptc.getAsJsonObject("judge").get("id").getAsInt());
    }
    
    @Test
    public void testUpdateProblemTestcaseMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.updateProblemTestcase("NON_EXISTING_CODE", 0, "updated input");
    }
    
    @Test
    public void testUpdateProblemTestcaseMethodNonexistingTestcase()
    {
    	exception.expect( NotFoundException.class );
    	client.updateProblemTestcase("TEST", 1, "updated input");
    }
    
    @Test
    public void testUpdateProblemTestcaseMethodNonexistingJudge()
    {
    	Integer nonexistingJudge = 9999;
    	
    	exception.expect( NotFoundException.class );
    	client.updateProblemTestcase("TEST", 0, "updated input", "updated output", 1.0, nonexistingJudge, false);
    }
    
    @Test
    public void testDeleteProblemTestcaseMethodSuccess()
    {
    	String r = randomNameGenerator();
    	// create problem and testcase to delete the testcase
    	String problem_code = "UT" + r;
    	String problem_name = "UT" + r;
    	
    	client.createProblem(problem_code, problem_name);
    	client.createProblemTestcase(problem_code, "in0", "out0", 1.0, 1, true);
    	client.createProblemTestcase(problem_code, "in1", "out1", 1.0, 1, true);
    	client.deleteProblemTestcase(problem_code, 0);
    	 
    	JsonObject p = client.getProblem(problem_code);
		assertEquals(1, p.getAsJsonArray("testcases").size());
		
		client.deleteProblemTestcase(problem_code, 1);
		
		p = client.getProblem(problem_code);
		assertEquals(0, p.getAsJsonArray("testcases").size());
    }
    
    @Test
    public void testDeleteProblemTestcaseMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.deleteProblemTestcase("NON_EXISTING_CODE", 0);
    }
    
    @Test
    public void testDeleteProblemTestcaseMethodNonexistingTestcase()
    {
    	exception.expect( NotFoundException.class );
    	client.deleteProblemTestcase("TEST", 1);
    }
    
    @Test
    public void testGetProblemTestcaseFileMethodSuccess()
    {
    	String r = randomNameGenerator();
    	// create problem and testcase to delete the testcase
    	String problem_code = "UT" + r;
    	String problem_name = "UT" + r;
    	
    	client.createProblem(problem_code, problem_name);
    	client.createProblemTestcase(problem_code, "in0", "out0", 1.0, 1, true);
    	
    	assertEquals("in0", client.getProblemTestcaseFile(problem_code, 0, "input"));
    	assertEquals("out0", client.getProblemTestcaseFile(problem_code, 0, "output"));
    }
    
    @Test
    public void testGetProblemTestcaseFileMethodNonexistingProblem()
    {
    	exception.expect( NotFoundException.class );
    	client.getProblemTestcaseFile("NON_EXISTING_CODE", 0, "input");
    }
    
    @Test
    public void testGetProblemTestcaseFileMethodNonexistingTestcase()
    {
    	exception.expect( NotFoundException.class );
    	client.getProblemTestcaseFile("TEST", 1, "input");
    }
    
    @Test
    public void testGetProblemTestcaseFileMethodNonexistingFile()
    {
    	exception.expect( NotFoundException.class );
    	client.getProblemTestcaseFile("TEST", 0, "fakefile");
    }
    
    @Test
    public void testGetJudgesMethodSuccess()
    {
    	assertEquals(10, 
    			client.getJudges()
    				.getAsJsonObject("paging")
    				.get("limit")
    				.getAsInt());
    	assertEquals(11, 
    			client.getJudges(11)
    				.getAsJsonObject("paging")
    				.get("limit")
    				.getAsInt());
    }
    
    @Test
    public void testGetJudgeMethodSuccess()
    {
    	assertEquals(1, client.getJudge(1).get("id").getAsInt());
    }
    
    @Test
    public void testGetJudgeMethodNonexistingJudge()
    {
    	Integer nonexistingJudge = 9999;
    	
    	exception.expect( NotFoundException.class );
    	client.getJudge(nonexistingJudge);
    }
    
    @Test
    public void testCreateJudgeMethodSuccess()
	{
		String judge_source = "source";
		Integer judge_compiler = 1;
		String judge_type = "testcase";
		String judge_name = "UT judge";
		
		JsonObject response = client.createJudge(
						judge_source,
						judge_compiler,
						judge_type,
						judge_name);
		Integer judge_id = response.get("id").getAsInt();
		assertTrue("Creation method should return new judge ID", judge_id > 0);
		
		JsonObject j = client.getJudge(judge_id);
		assertEquals("Judge source", judge_source, j.get("source").getAsString());
		assertEquals("Judge compiler ID", judge_compiler, (Integer) j.getAsJsonObject("compiler").get("id").getAsInt());
		assertEquals("Judge type", judge_type, j.get("type").getAsString());
		assertEquals("Judge name", judge_name, j.get("name").getAsString());
	}
    
    @Test
    public void testCreateJudgeMethodEmptySource()
	{
    	exception.expect( BadRequestException.class );
		client.createJudge("", 1, "testcase", "");
	}
	
    @Test
	public void testCreateJudgeMethodNonexistingCompiler()
	{
		Integer nonexistingCompiler = 9999;
		
		exception.expect( NotFoundException.class );
		client.createJudge("nonempty source", nonexistingCompiler, "testcase", "");
	}
    
    @Test
    public void testUpdateJudgeMethodSuccess()
	{
		JsonObject response = client.createJudge("source", 1, "testcase", "UT judge");
		Integer judge_id = response.get("id").getAsInt();
		 
		String new_judge_source = "updated source";
		Integer new_judge_compiler = 11;
		String new_judge_name = "UT judge updated";
		
		client.updateJudge(
				judge_id,
				new_judge_source,
				new_judge_compiler,
				new_judge_name);
		
		JsonObject j = client.getJudge(judge_id);
		assertEquals("Judge source", new_judge_source, j.get("source").getAsString());
		assertEquals("Judge compiler ID", new_judge_compiler, (Integer) j.getAsJsonObject("compiler").get("id").getAsInt());
		assertEquals("Judge name", new_judge_name, j.get("name").getAsString());
	}
    
    @Test
    public void testUpdateJudgeMethodEmptySource()
	{
		JsonObject response = client.createJudge("source", 1, "testcase", "UT judge");
		Integer judge_id = response.get("id").getAsInt();
		
		exception.expect( BadRequestException.class );
		client.updateJudge(judge_id, "", 1, "");
	}
	
    @Test
	public void testUpdateJudgeMethodNonexistingJudge()
	{
		Integer nonexistingJudge = 99999999;
		
		exception.expect( NotFoundException.class );
		client.updateJudge(nonexistingJudge, "nonempty source", 1, "");
	}
	
    @Test
	public void testUpdateJudgeMethodNonexistingCompiler()
	{
		JsonObject response = client.createJudge("source", 1, "testcase", "UT judge");
		Integer judge_id = response.get("id").getAsInt();
		Integer nonexistingCompiler = 9999;
	
		exception.expect( NotFoundException.class );
		client.updateJudge(judge_id, "nonempty source", nonexistingCompiler, "");
	}
	
    @Test
	public void testUpdateJudgeMethodForeignJudge()
	{
    	exception.expect( ForbiddenException.class );
		client.updateJudge(1, "nonempty source", 1, "");
	}
    
    @Test
    public void testGetSubmissionMethodSuccess()
	{
		assertEquals(1, client.getSubmission(1).get("id").getAsInt());
	}
    
    @Test
    public void testGetSubmissionMethodNonexistingSubmission()
	{
		Integer nonexistingSubmission = 999999999;
		
		exception.expect( NotFoundException.class );
		client.getSubmission(nonexistingSubmission);
	}
    
    @Test
    public void testGetSubmissionsMethodSuccess()
	{
    	
    	JsonObject response = client.getSubmissions(new Integer[]{1,2});
    	
    	assertEquals(true, response.isJsonArray() && response.get("items") != null);   
    	assertEquals(2, response.get("items").getAsJsonArray().size());
    	assertEquals(true, response.get("items").getAsJsonArray().get(0).isJsonNull() == false);
    	assertEquals(true, response.get("items").getAsJsonArray().get(1).isJsonNull() == false);
		
	}
    
    @Test
    public void testGetSubmissionsMethodNonexistingSubmission()
	{
		JsonObject response = client.getSubmissions(new Integer[]{999999999});
		
		assertEquals(true, response.isJsonArray() && response.get("items") != null);   
    	assertEquals(0, response.get("items").getAsJsonArray().size());
	}
    
    @Test
    public void testCreateSubmissionMethodSuccess()
	{
		String submission_problem_code = "TEST";
		String submission_source = "source";
		Integer submission_compiler = 1;
		
		JsonObject response = client.createSubmission(
				submission_problem_code,
				submission_source,
				submission_compiler);
		Integer submission_id = response.get("id").getAsInt();
		
		assertTrue("Creation method should return new submission ID", submission_id > 0);
		JsonObject s = client.getSubmission(submission_id);
		
		assertEquals("Submission problem code", submission_problem_code, s.getAsJsonObject("problem").get("code").getAsString());
		assertEquals("Submission source", submission_source, s.get("source").getAsString());
		assertEquals("Submission compiler ID", submission_compiler, (Integer) s.getAsJsonObject("compiler").get("id").getAsInt());
	}
    
    @Test
    public void testCreateSubmissionMethodEmptySource()
	{
    	exception.expect( BadRequestException.class );
		client.createSubmission("TEST", "", 1);
	}
	
    @Test
	public void testCreateSubmissionMethodNonexistingProblem()
	{
    	exception.expect( NotFoundException.class );
		client.createSubmission("NON_EXISTING_CODE", "nonempty source", 1);
	}
	
    @Test
	public void testCreateSubmissionMethodNonexistingCompiler()
	{
		Integer nonexistingCompiler = 9999;
		
		exception.expect( NotFoundException.class );
		client.createSubmission("TEST", "nonempty source", nonexistingCompiler);
	}
	
    @Test
	public void testCreateSubmissionMethodNonexistingUser()
	{
		Integer nonexistingUser = 999999999;
	
		exception.expect( NotFoundException.class );
		client.createSubmission("TEST", "nonempty source", 1, nonexistingUser);
	}
}
