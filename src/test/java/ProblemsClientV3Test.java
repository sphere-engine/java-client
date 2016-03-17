package test.java;

import static org.junit.Assert.*;

import java.util.Map;

import javax.ws.rs.NotAuthorizedException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.SphereEngine.Api.ProblemsClientV3;

public class ProblemsClientV3Test 
{
	static Map<String, String> env;
	
	@BeforeClass
	static public void setUpBeforeClass()
	{
		env = System.getenv();
	}
	
	@Test
	public void testAutorizationFail() 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				"wrong access token", 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		try {
			client.test();
			assertTrue(false);
		} catch (NotAuthorizedException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAutorizationSuccess() 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		try {
			client.test();
			assertTrue(true);
		} catch (NotAuthorizedException e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testTestMethodSuccess() 
	{
		ProblemsClientV3 client = new ProblemsClientV3(
				env.get("SE_ACCESS_TOKEN_PROBLEMS"), 
				env.get("SE_ENDPOINT_PROBLEMS"));
		
		assertTrue(client.test().get("message").getAsString().length() > 0);
	}
}
