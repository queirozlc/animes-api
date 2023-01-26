package com.lucas.animes.exception.handler;

import com.lucas.animes.exception.BadRequestException;
import com.lucas.animes.exception.BadRequestExceptionDetails;
import com.lucas.animes.exception.ExceptionDetails;
import com.lucas.animes.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException e) {
		return new ResponseEntity<>
				(
						BadRequestExceptionDetails
								.builder()
								.timestamp(LocalDateTime.now())
								.status(HttpStatus.BAD_REQUEST.value())
								.title("Bad Request Exception. Check the api docs.")
								.details(e.getClass().getName())
								.message(e.getMessage())
								.build(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException e,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		String fields = fieldErrors
				.stream()
				.map(FieldError::getField)
				.collect(Collectors.joining(","));

		String fieldsMessage = fieldErrors
				.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(","));

		return new ResponseEntity<>
				(
						ValidationExceptionDetails
								.builder()
								.timestamp(LocalDateTime.now())
								.status(HttpStatus.BAD_REQUEST.value())
								.title("Bad Request Exception. Invalid fields. Check the api docs.")
								.details(e.getClass().getName())
								.message("Check the field(s) error")
								.fields(fields)
								.fieldsMessage(fieldsMessage)
								.build(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception e,
			@Nullable Object body,
			HttpHeaders headers,
			HttpStatusCode statusCode,
			WebRequest request) {

		ExceptionDetails exceptionDetails = ExceptionDetails
				.builder()
				.timestamp(LocalDateTime.now())
				.status(statusCode.value())
				.title(e.getCause().getMessage())
				.details(e.getClass().getName())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(exceptionDetails, headers, statusCode);
	}
}
