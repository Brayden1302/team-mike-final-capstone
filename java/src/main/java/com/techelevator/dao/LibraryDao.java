package com.techelevator.dao;

import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;

import java.awt.print.Book;
import java.util.List;

public interface LibraryDao {

    BookDto addBook(BookDto book) throws DuplicateBookException;
    List<BookDto> getBooks();
}
