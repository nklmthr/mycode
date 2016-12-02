package org.personal.myapp.mydriver.excpetion;

import org.springframework.http.HttpStatus;

public class BaseHttpException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;

	public void setStatus(HttpStatus httpReturnCode) {
		status = httpReturnCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	public BaseHttpException(String message, HttpStatus httpErrorCode) {
		setStatus(httpErrorCode);
		setMessage(message);
	}

	public BaseHttpException(Throwable cause, HttpStatus httpErrorCode) {
		super(cause);
		setStatus(httpErrorCode);
		setMessage(cause.getMessage());
	}
	
	public BaseHttpException(String message, Throwable cause) {
		super(message, cause);
	}
}
