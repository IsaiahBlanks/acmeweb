package com.acme.statusmgr.beans;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown when the Detail List is empty. Returns a response to spring that a BAD_REQUEST was issued
 * and states the reason for the exception
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Required List parameter 'details' is not present in request")
public class EmptyDetailListException extends RuntimeException {
}
