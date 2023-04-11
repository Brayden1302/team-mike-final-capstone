package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Duplicate book in library")
public class DuplicateBookException extends Exception {

    public DuplicateBookException() {
        super("Book already in library");
    }
}
