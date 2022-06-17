package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class SemesterNotFoundException extends NoSuchElementException {
    public SemesterNotFoundException(String key) {
        super(String.format(
                "Semester with key '%s' does not exist",
                key
        ));
    }
}
