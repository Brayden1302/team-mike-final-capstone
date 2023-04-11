package com.techelevator.dao;

import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;

public interface LibraryDao {

    BookDto addBook(BookDto book) throws DuplicateBookException;

}
