package com.techelevator.dao;

import com.techelevator.model.BookDto;
import org.springframework.stereotype.Component;

@Component
public class JdbcLibraryDao implements LibraryDao{

    @Override
    public BookDto addBook(BookDto book) {
        if (book != null) {
            return book;
        }
        else {
            return book;
        }
    }
}
