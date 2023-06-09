package com.rpconsulting.app.invoicing.exceptions;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends RuntimeException {

	public AlreadyExistsException(String message) {
		super(message);
	}
}
