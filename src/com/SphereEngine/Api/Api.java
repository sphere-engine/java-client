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
     * Instance of the Compilers 
     */
    private Compilers compilers = null;
    
    /**
     * Instance of the Problems Module 
     */
    private Problems problems = null;
    
    /**
     * Instance of API Settings
     */
    private Settings settings = null;

    /**
     * Constructor
     * 
     * @param {Settings} settings - API settings
     */
    public Api(Settings settings)
    {
        this.settings = settings;
    }
    
    /**
     * Setter for settings.
     * 
     * @param {Settings} settings - API settings
     * @return The API object
     */
    public Api setSettings(Settings settings)
    {
    	this.settings = settings;
    	this.compilers = null;
    	this.problems = null;
    	
    	return this;
    }

    /**
     * Retrieve Compilers Module client
     * 
     * @return Compilers Module client
     */
    public Compilers getCompilersClient()
    {
        if (compilers == null) {
            compilers = new Compilers(settings);
        }
        return compilers;
    }

    /**
     * Retrieve Problems Module client
     * 
     * @return Problems Module client
     */
    public Problems getProblemsClient()
    {
        if (problems == null) {
            problems = new Problems(settings);
        }
        return problems;
    }
}
