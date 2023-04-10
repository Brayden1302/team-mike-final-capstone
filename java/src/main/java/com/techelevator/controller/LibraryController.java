package com.techelevator.controller;

import com.techelevator.dao.LibraryDao;
import com.techelevator.model.BookDto;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.awt.print.Book;

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
    public BookDto addBook( @RequestBody BookDto book) {
        return dao.addBook(book);
    }




}
