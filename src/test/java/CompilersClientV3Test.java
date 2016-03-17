package test.java;

import static org.junit.Assert.*;

import java.util.Map;

import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.SphereEngine.Api.CompilersClientV3;

import com.google.gson.JsonObject;

public class CompilersClientV3Test 
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
		CompilersClientV3 client = new CompilersClientV3(
				"wrong access token", 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		assertThat("Wrong access token should implies empty response", client.test(), is(equalTo(new JsonObject())));
	}

	@Test
	public void testAutorizationSuccess() 
	{
		CompilersClientV3 client = new CompilersClientV3(
				env.get("SE_ACCESS_TOKEN_COMPILERS"), 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		System.out.println(client.test());
		assertThat(client.test(), not(equalTo(new JsonObject())));
	}
	
	@Test
	public void testTestMethodSuccess() 
	{
		CompilersClientV3 client = new CompilersClientV3(
				env.get("SE_ACCESS_TOKEN_COMPILERS"), 
				env.get("SE_ENDPOINT_COMPILERS"));
		
		assertThat(client.test().get("pi").getAsDouble(), is(3.14));
	}
}
