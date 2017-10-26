package com.SphereEngine.Api.Exception;

public class BadRequestException extends ClientException {
	
	public BadRequestException(String message) {
        super(message, 400);
    }
	
}