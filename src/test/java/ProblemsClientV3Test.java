package test.java;

import static org.junit.Assert.*;

import java.util.Map;

import javax.ws.rs.NotAuthorizedException;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.SphereEngine.Api.ProblemsClientV3;

public class ProblemsClientV3Test 
{
	static Map<String, String> env;
	static ProblemsClientV3 client;
	
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
}
