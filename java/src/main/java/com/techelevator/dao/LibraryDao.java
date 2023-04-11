package com.techelevator.dao;

import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;

import java.awt.print.Book;
import java.security.Principal;
import java.util.Date;
import java.util.List;

public interface LibraryDao {

    BookDto addBook(BookDto book) throws DuplicateBookException;
    List<BookDto> getBooks();
    Date addSearchDate( String user);


}
