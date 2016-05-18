package org.personal.exception;

public class PlayerException extends Exception {


	public PlayerException() {
		super();
	}

	public PlayerException(String msg) {
		super(msg);
	}
	public PlayerException(Exception ex, String msg){
		super(msg, ex);
	}
}
