package com.SphereEngine.Api;

/*
 * @author	Sphere Research Labs Sp z o.o.
 * @version	0.1
 * @since	02/26/2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class Settings 
{
	private String compilers_access_token = "";
	private String compilers_version = "v3";
	private String compilers_endpoint = null;
	
	private String problems_access_token = "";
	private String problems_version = "v3";
	private String problems_endpoint = null;
	
	public Settings setCompilersAccessToken(String accessToken)
	{
		compilers_access_token = accessToken;
		
		return this;
	}
	
	public Settings setCompilersVersion(String version)
	{
		compilers_version = version;
		
		return this;
	}
	
	public Settings setCompilersEndpoint(String endpoint)
	{
		compilers_endpoint = endpoint;
		
		return this;
	}
	
	public Settings setProblemsAccessToken(String accessToken)
	{
		problems_access_token = accessToken;
		
		return this;
	}
	
	public Settings setProblemsVersion(String version)
	{
		problems_version = version;
		
		return this;
	}
	
	public Settings setProblemsEndpoint(String endpoint)
	{
		problems_endpoint = endpoint;
		
		return this;
	}
	
	public String getCompilersAccessToken()
	{
		return compilers_access_token;
	}
	
	public String getCompilersVersion()
	{
		return compilers_version;
	}
	
	public String getCompilersEndpoint()
	{
		return compilers_endpoint;
	}
	
	public String getProblemsAccessToken()
	{
		return problems_access_token;
	}
	
	public String getProblemsVersion()
	{
		return problems_version;
	}
	
	public String getProblemsEndpoint()
	{
		return problems_endpoint;
	}
}
