package com.techelevator.dao;

import com.techelevator.model.BookDto;
import com.techelevator.model.DuplicateBookException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcLibraryDao implements LibraryDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLibraryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BookDto addBook(BookDto book) throws DuplicateBookException {
        Integer publisherId = null;
        Integer bookId = null;
        String getPublisherIdSql = "SELECT publisher_id FROM publishers WHERE name = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(getPublisherIdSql, book.getPublisher());
        if (row.next()) {
            publisherId = row.getInt("publisher_id");
        }
        else {
            String publisherSql = "insert into publishers(name) values(?) RETURNING publisher_id";
            publisherId = jdbcTemplate.queryForObject(publisherSql, Integer.class, book.getPublisher());
        }
        String getBookSql = "SELECT * FROM books WHERE isbn = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getBookSql, book.getIsbn());
        if (results.next()) {
            throw new DuplicateBookException();
        }
        else {
            String bookSql =
                    "insert into books(title, publisher_id ,date_added, isbn, page_count, description, publish_date, image_link) " +
                            "values(?, ? , current_date, ?, ?, ?, ?, ? ) RETURNING book_id;";
             bookId = jdbcTemplate.queryForObject(bookSql, Integer.class, book.getTitle(),
                    publisherId, book.getIsbn(), book.getPageCount(), book.getDescription(), book.getPublishedDate(), book.getImageLink());
        }
        String[] categories = book.getCategories();
        for (String category : categories) {
            String categorySql = " SELECT category_id FROM categories WHERE name= ?";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(categorySql, category);
            if (rowSet.next()) {
                Integer categoryId = rowSet.getInt("category_id");
                String bookCategorySql = " insert into books_categories(category_id, book_id) " +
                        "values ( ?, ? );";
                jdbcTemplate.update(bookCategorySql, categoryId, bookId);

            } else{
                String addCategorySql = " insert into categories(name) "  +
                        "values (?) RETURNING category_id; ";
                Integer categoryId = jdbcTemplate.queryForObject(addCategorySql, Integer.class, category);
                String bookCategorySql = " insert into books_categories(category_id, book_id) " +
                        "values ( ?, ? );";
                jdbcTemplate.update(bookCategorySql, categoryId, bookId);
            }

        }
            String[] authors = book.getAuthors();
        for (String author : authors) {
            String authorSql = " SELECT author_id FROM authors WHERE name = ?";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(authorSql, author);

            if (rowSet.next()) {
                Integer authorId = rowSet.getInt("author_id");
                String bookAuthorSql = " insert into books_authors(author_id, book_id) " +
                        "values ( ?, ? );";
                jdbcTemplate.update(bookAuthorSql, authorId, bookId);

            } else{
                String addCategorySql = " insert into authors(name) "  +
                        "values (?) RETURNING author_id; ";
                Integer authorId = jdbcTemplate.queryForObject(addCategorySql, Integer.class, author);
                String bookCategorySql = " insert into books_authors(author_id, book_id) " +
                        "values ( ?, ? );";
                jdbcTemplate.update(bookCategorySql, authorId, bookId);
            }

        }

        return book;
    }
}