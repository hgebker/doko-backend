package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class DuplicateExpenseException extends IllegalStateException{
    public DuplicateExpenseException(String message) {
        super(message);
    }
}
