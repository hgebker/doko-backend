package com.hgebk.dokobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends NoSuchElementException {
    public ExpenseNotFoundException(String description) {
        super(String.format(
                "Expense with description '%s' does not exist",
                description
        ));
    }
}
