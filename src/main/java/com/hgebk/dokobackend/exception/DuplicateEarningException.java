package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Duplicate earning")
public class DuplicateEarningException extends IllegalStateException{
    public DuplicateEarningException(String message) {
        super(message);
    }
}
