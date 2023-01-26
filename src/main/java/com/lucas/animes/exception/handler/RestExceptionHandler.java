package com.lucas.animes.exception.handler;

import com.lucas.animes.exception.BadRequestException;
import com.lucas.animes.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException e) {
		return new ResponseEntity<>(BadRequestExceptionDetails.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Bad Request Exception, check api docs.")
				.details(e.getClass().getName())
				.message(e.getMessage())
				.build(), HttpStatus.BAD_REQUEST);
	}
}
