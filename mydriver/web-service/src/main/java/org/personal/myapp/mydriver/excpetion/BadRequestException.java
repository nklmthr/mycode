package org.personal.myapp.mydriver.excpetion;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseHttpException {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

	public BadRequestException(Throwable cause) {
		super(cause, HttpStatus.BAD_REQUEST);
	}

}
