package com.SphereEngine.Api;

import com.SphereEngine.Api.Modules.Compilers;
import com.SphereEngine.Api.Modules.Problems;

/*
 * SphereEngine.Api.Api
 * 
 * 0.1
 *
 * 18.01.2016
 * 
 * Copyright 2015 Sphere Research Sp z o.o.
 */
public class Api
{
    
    /**
     * Compilers module
     * @param SphereEngine.Api.Compilers instance of the Compilers
     */
    private Compilers compilers = null;
    
    /**
     * Problems module
     * @param SphereEngine.Api.Problems instance of the Problems
     */
    private Problems problems = null;
    
    private String accessToken = null;
    private String version = null;
    private String endpoint = null;

    public Api(String accessToken, String version, String endpoint)
    {
        this.accessToken = accessToken;
        this.version = version;
        this.endpoint = endpoint;
    }

    /**
     * @return SphereEngine.Api.Compilers compilers module client
     */
    public Compilers getCompilersClient()
    {
        if (compilers == null) {
            compilers = new Compilers(accessToken, version, endpoint);
        }
        return compilers;
    }

    /**
     * @return SphereEngine.Api.Problems problems module client
     */
    public Problems getProblemsClient()
    {
        if (problems == null) {
            problems = new Problems(accessToken, version, endpoint);
        }
        return problems;
    }
}
