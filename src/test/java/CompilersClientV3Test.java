package test.java;

import static org.junit.Assert.*;

import java.util.Map;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ForbiddenException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.SphereEngine.Api.CompilersClientV3;

import com.google.gson.JsonObject;

public class CompilersClientV3Test 
{
	static Map<String, String> env;
	static CompilersClientV3 client;
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@BeforeClass
	static public void setUpBeforeClass()
	{
		env = System.getenv();
		client = new CompilersClientV3(
				env.get("SE_ACCESS_TOKEN_COMPILERS"), 
				env.get("SE_ENDPOINT_COMPILERS"));
	}
	
	@Test
	public void testAutorizationFail() 
	{
		CompilersClientV3 badclient = new CompilersClientV3(
				"wrong access token", 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		exception.expect( NotAuthorizedException.class );
		badclient.test();
	}

	@Test
	public void testAutorizationSuccess() 
	{
		assertThat(client.test(), not(equalTo(new JsonObject())));
	}
	
	@Test
	public void testTestMethodSuccess() 
	{
		assertThat(client.test().get("pi").getAsDouble(), is(3.14));
	}
	
	@Test
	public void testCompilersMethodSuccess() 
	{
		assertThat(client.getCompilers().get("11").getAsString().charAt(0), is('C'));
	}
	
	@Test
	public void testGetSubmissionMethodSuccess()
    {
		JsonObject s = client.getSubmission(2, true);
        assertEquals("abc", s.get("source").getAsString());
        assertEquals(11, s.getAsJsonObject("compiler").get("id").getAsInt());
    }
	
	@Test
	public void testGetSubmissionMethodNotExisting()
    {
    	Integer nonexistingSubmission = 3;
    	
    	exception.expect( NotFoundException.class );
    	client.getSubmission(nonexistingSubmission);
    }
	
	@Test
	public void testGetSubmissionMethodAccessDenied()
    {
    	Integer foreignSubmission = 1;
    	
    	exception.expect( ForbiddenException.class );
    	client.getSubmission(foreignSubmission);
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
	public void testGetSubmissionStreamMethodSuccess()
    {
		String source = client.getSubmissionStream(2, "output");
        assertEquals("abc", source);
    }

	@Test
	public void testGetSubmissionStreamMethodNotExistingSubmission()
    {
    	Integer nonexistingSubmission = 3;
    	
    	exception.expect( NotFoundException.class );
    	client.getSubmissionStream(nonexistingSubmission, "output");
    }

	@Test
	public void testGetSubmissionStreamMethodNotExistingStream()
    {
    	exception.expect( NotFoundException.class );
    	client.getSubmissionStream(2, "nonexistingstream");
    }

	@Test
	public void testCreateSubmissionMethodSuccess()
    {
		String submission_source = "unit test";
		Integer submission_compiler = 11;
		String submission_input = "unit test input";
		JsonObject response = client.createSubmission(submission_source, submission_compiler, submission_input);
		Integer submission_id = response.get("id").getAsInt(); 
        
		assertTrue("New submission id should be greater than 0", submission_id > 0);
		
		JsonObject s = client.getSubmission(submission_id, true, true);
		assertEquals("Submission source", submission_source, s.get("source").getAsString());
		assertEquals("Submission input", submission_input, s.get("input").getAsString());
		assertEquals("Submission compiler ID", submission_compiler, (Integer) s.getAsJsonObject("compiler").get("id").getAsInt());
    }
	
	@Test
	public void testCreateSubmissionMethodWrongCompiler()
    {
    	Integer wrong_compiler_id = 9999;
    	
    	exception.expect( NotFoundException.class );
    	client.createSubmission("unit_test", wrong_compiler_id);
    }
}
