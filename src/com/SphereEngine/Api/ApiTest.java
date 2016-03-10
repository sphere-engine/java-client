package com.SphereEngine.Api;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.SphereEngine.Api.Modules.Compilers;
import com.google.gson.JsonObject;

public class ApiTest 
{

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
				.setCompilersAccessToken("e7a5298eaab2666705871a0d6afaef37")
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
				.setCompilersAccessToken("e7a5298eaab2666705871a0d6afaef37")
				.setCompilersVersion("v3")
				.setCompilersEndpoint("robson");
		
		Api api = new Api(settings);
		Compilers c_client = api.getCompilersClient();
		
		assertThat(c_client.test().get("pi").getAsDouble(), is(3.13));
	}
}
