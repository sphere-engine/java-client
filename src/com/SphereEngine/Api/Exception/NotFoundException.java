package com.SphereEngine.Api.Exception;

public class NotFoundException extends ClientException {
	
	public NotFoundException(String message) {
        super(message, 404);
    }
	
}