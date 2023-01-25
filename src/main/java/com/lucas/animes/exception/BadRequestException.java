package com.lucas.animes.exception;

public class BadRequestException extends RuntimeException{

	public BadRequestException(String message) {
		super(message);
	}

	static public <T> T requireNonNull(T obj, String message) {
		if (obj == null) {
			throw new BadRequestException(message);
		}
		return obj;
	}

}
