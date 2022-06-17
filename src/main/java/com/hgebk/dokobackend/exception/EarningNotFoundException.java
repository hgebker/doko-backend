package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class EarningNotFoundException extends NoSuchElementException {
    public EarningNotFoundException(String description) {
        super(String.format(
                "Earning with art '%s' does not exist",
                description
        ));
    }
}
