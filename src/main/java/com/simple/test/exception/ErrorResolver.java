package com.simple.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorResolver extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransactionServiceException.class)
	protected ResponseEntity<Object> handleTransactionServiceException(final TransactionServiceException trEx) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND);
		error.setMessage(trEx.getMessage());
		return new ResponseEntity<>(error, error.getStatus());
	}
}
