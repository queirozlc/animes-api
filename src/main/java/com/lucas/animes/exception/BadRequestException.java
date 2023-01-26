package com.lucas.animes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	public BadRequestException(String message) {
		super(message);
	}

	static public <T> void requireNonNull(T obj, String message) {
		if (obj == null) {
			throw new BadRequestException(message);
		}
	}

}
