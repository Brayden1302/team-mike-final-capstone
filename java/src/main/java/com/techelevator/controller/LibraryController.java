package com.techelevator.controller;

import com.techelevator.dao.LibraryDao;
import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return dao.getBooks();
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public LocalDate userSearchDate(Principal user) {
        return dao.addSearchDate( user.getName());
    }
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public LocalDate getUserSearchDate(Principal user) {
        return dao.getUserSearchDate( user.getName());
    }


}
