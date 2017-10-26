package com.SphereEngine.Api.Exception;

public class ConnectionException extends Exception {
	
	protected int code;
	
	public ConnectionException(String message, int code) {
		super(message);
		this.code = code;
    }
	
	public int getCode() {
		return code;
	}
	
}