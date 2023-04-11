package com.techelevator.controller;

import com.techelevator.dao.LibraryDao;
import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LibraryController {

    @Autowired
    private LibraryDao dao;

    @RequestMapping(path="/hello", method= RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public BookDto addBook( @RequestBody BookDto book) throws DuplicateBookException {
        return dao.addBook(book);
    }




}
