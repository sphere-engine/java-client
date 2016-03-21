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
	static CompilersClientV3 client;
	
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
		
		assertThat("Wrong access token should implies empty response", badclient.test(), is(equalTo(new JsonObject())));
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
}
