package com.SphereEngine.Api.Exception;

public class ConflictException extends ClientException {
	
	public ConflictException(String message) {
        super(message, 409);
    }
	
}