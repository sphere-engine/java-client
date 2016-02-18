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
     * @var SphereEngine.Api.Compilers instance of the Compilers
     */
    private Compilers compilers = null;
    
    /**
     * Problems module
     * @var SphereEngine.Api.Problems instance of the Problems
     */
    private Problems problems = null;
    
    private String accessToken = null;
    private String version = null;
    private String endpoint = null;

    public Api(_accessToken, _version, _endpoint)
    {
        accessToken = _accessToken;
        version = _version;
        endpoint = _endpoint;
    }

    /**
     * @return SphereEngine.Api.Compilers compilers module client
     */
    public function getCompilersClient()
    {
        if (compilers == null) {
            compilers = new Compilers(accessToken, version, endpoint);
        }
        return compilers;
    }

    /**
     * @return SphereEngine.Api.Problems problems module client
     */
    public function getProblemsClient()
    {
        if (problems == null) {
            problems = new Problems(accessToken, version, endpoint);
        }
        return problems;
    }
}
