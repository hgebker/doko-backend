package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class EveningNotFoundException extends NoSuchElementException {
    public EveningNotFoundException(String date) {
        super(String.format(
                "Evening with date '%s' does not exist",
                date
        ));
    }
}
