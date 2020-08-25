package com.simple.test.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private HttpStatus status;

	private String message;

	public ErrorResponse(HttpStatus status) {
		this.status = status;
	}

}
