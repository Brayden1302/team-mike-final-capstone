package com.techelevator.controller;

import com.techelevator.dao.LibraryDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;
import com.techelevator.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class LibraryController {

    @Autowired
    private LibraryDao dao;
    @Autowired
    private UserDao userDao;

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
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/readinglist/{bookId}", method = RequestMethod.POST)
    public void updateReadingList(@PathVariable int bookId, Principal user) {
         dao.addToReadingList(bookId, userDao.findIdByUsername(user.getName()));
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/readinglist/{bookId}", method = RequestMethod.DELETE)
    public void deleteReadingList(@PathVariable int bookId, Principal user) {
        dao.deleteFromReadingList(bookId, userDao.findIdByUsername(user.getName()));

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/readinglist", method = RequestMethod.GET)
    public List<BookDto> getTheReadingList( Principal user ){
        if (user != null) {
            return dao.getReadingList(userDao.findIdByUsername(user.getName()));
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/readinglist/{id}", method = RequestMethod.PUT)
    public void markBookReadNotRead(@PathVariable int id, Principal user) {
         dao.markBookRead(id, userDao.findIdByUsername(user.getName()));
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public LocalDate userSearchDate(Principal user) {
        return dao.addSearchDate( user.getName());
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public LocalDate getUserSearchDate(Principal user) {
        if (user != null) {
            return dao.getUserSearchDate(user.getName());
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/genres", method = RequestMethod.GET)
    public List<String> getGenres() {
        return dao.getGenres();
    }





}
