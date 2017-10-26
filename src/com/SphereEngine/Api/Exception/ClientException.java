package com.SphereEngine.Api.Exception;

public class ClientException extends Exception {
	
	protected int code;
	
	public ClientException(String message, int code) {
		super(message);
		this.code = code;
    }
	
	public int getCode() {
		return code;
	}
	
}