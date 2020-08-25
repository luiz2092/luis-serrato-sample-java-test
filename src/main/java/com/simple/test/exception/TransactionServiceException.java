package com.simple.test.exception;

public class TransactionServiceException extends RuntimeException {

	/**
	 * Default UID.
	 */
	private static final long serialVersionUID = 1L;

	public TransactionServiceException(String message) {
		super(message);
	}

}
