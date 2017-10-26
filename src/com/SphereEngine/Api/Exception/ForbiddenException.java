package com.SphereEngine.Api.Exception;

public class ForbiddenException extends ClientException {
	
	public ForbiddenException(String message) {
        super(message, 403);
    }
	
}