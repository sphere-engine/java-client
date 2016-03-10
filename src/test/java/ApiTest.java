package test.java;

import static org.junit.Assert.*;

import java.util.Map;

import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.SphereEngine.Api.Api;
import com.SphereEngine.Api.Settings;
import com.SphereEngine.Api.Modules.Compilers;
import com.google.gson.JsonObject;

public class ApiTest 
{
	static Map<String, String> env;
	
	@BeforeClass
	static public void setUpBeforeClass()
	{
		env = System.getenv();
	}
	
	@Test
	public void testCompilersAutorizationFail() 
	{
		Settings settings = new Settings()
				.setCompilersAccessToken("wrong access token")
				.setCompilersVersion("v3")
				.setCompilersEndpoint("robson");
		
		Api api = new Api(settings);
		Compilers c_client = api.getCompilersClient();
		
		assertThat("Wrong access token should implies empty response", c_client.test(), is(equalTo(new JsonObject())));
	}

	@Test
	public void testCompilersAutorizationSuccess() 
	{
		Settings settings = new Settings()
				.setCompilersAccessToken(env.get("SE_ACCESS_TOKEN_COMPILERS"))
				.setCompilersVersion("v3")
				.setCompilersEndpoint("robson");
		
		Api api = new Api(settings);
		Compilers c_client = api.getCompilersClient();
		
		assertThat(c_client.test(), not(equalTo(new JsonObject())));
	}
	
	@Test
	public void testCompilersTestMethodSuccess() 
	{
		Settings settings = new Settings()
				.setCompilersAccessToken(env.get("SE_ACCESS_TOKEN_COMPILERS"))
				.setCompilersVersion("v3")
				.setCompilersEndpoint("robson");
		
		Api api = new Api(settings);
		Compilers c_client = api.getCompilersClient();
		
		assertThat(c_client.test().get("pi").getAsDouble(), is(3.14));
	}
}
