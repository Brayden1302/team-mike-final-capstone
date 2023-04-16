package com.techelevator.dao;

import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;

import java.time.LocalDate;
import java.util.List;

public interface LibraryDao {

    BookDto addBook(BookDto book) throws DuplicateBookException;
    List<BookDto> getBooks();
    LocalDate addSearchDate(String user);
    LocalDate getUserSearchDate(String username);
    void addToReadingList(int bookId, int userId);
    void deleteFromReadingList(int bookId, int userId);
    List<BookDto> getReadingList(int userId);
    List<String> getGenres();
    void markBookRead(int bookId, int userId);
}
