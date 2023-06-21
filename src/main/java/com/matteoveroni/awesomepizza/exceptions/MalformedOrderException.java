package com.matteoveroni.awesomepizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The order is malformed")
public class MalformedOrderException extends RuntimeException {

    public MalformedOrderException() {
        super("The order is malformed");
    }

    public MalformedOrderException(String message) {
        super(message);
    }
}
