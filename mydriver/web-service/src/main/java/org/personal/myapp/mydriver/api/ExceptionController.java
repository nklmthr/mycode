package org.personal.myapp.mydriver.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.personal.myapp.mydriver.excpetion.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.personal.myapp.mydriver.model.Error;
@ControllerAdvice
public class ExceptionController {

	private Log log = LogFactory.getLog(getClass());

	@ExceptionHandler
	public ResponseEntity<Error> handleCustomException(BaseHttpException exception) {
		log.error(exception.getMessage(), exception);
		Error error = new Error();
		error.setCode(exception.getStatus().value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<Error>(error, exception.getStatus());
	}

	@ExceptionHandler
	public ResponseEntity<Error> handleException(Throwable throwable) {
		log.error(throwable.getMessage(), throwable);
		Error error = new Error();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(throwable.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
