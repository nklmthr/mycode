package org.personal.games.game29.exception;

public class RefreshException extends Exception {


	public RefreshException() {
		super();
	}

	public RefreshException(String msg) {
		super(msg);
	}

	public RefreshException(Exception ex, String msg) {
		super(msg, ex);
	}
}
