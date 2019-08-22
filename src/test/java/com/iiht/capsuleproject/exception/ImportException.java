package com.iiht.capsuleproject.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Required values not found")
public class ImportException extends RuntimeException{
	private static final long serialVersionUID =3935230281455340069L;
}
