package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class DuplicateEarningException extends IllegalStateException{
    public DuplicateEarningException(String description) {
        super(String.format(
                "Earning with description %s already exists",
                description
        ));
    }
}
