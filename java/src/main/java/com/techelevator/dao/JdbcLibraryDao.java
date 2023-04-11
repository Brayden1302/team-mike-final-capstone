package com.techelevator.dao;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.techelevator.model.BookDto;
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
    public BookDto addBook(BookDto book) {
        String publisherSql = "insert into publishers(name) values(?) RETURNING publisher_id";
        Integer publisherId = jdbcTemplate.queryForObject(publisherSql, Integer.class, book.getPublisher());
        String bookSql =
                "insert into books(title, publisher_id ,date_added, isbn, page_count, description, publish_date, image_link) " +
                "values(?, ? , current_date, ?, ?, ?, ?, ? ) RETURNING book_id;";
        Integer bookId = jdbcTemplate.queryForObject(bookSql, Integer.class, book.getTitle(),
                publisherId, book.getIsbn(), book.getPageCount(), book.getDescription(), book.getPublishedDate(), book.getImageLink());
        String[] categories = book.getCategories();
        for (String category : categories) {
            String categorySql = " SELECT category_id FROM categories WHERE name= ?";
//            Integer categoryId = jdbcTemplate.queryForObject(categorySql, Integer.class, category);

            SqlRowSet row = jdbcTemplate.queryForRowSet(categorySql, category);
            if (row.next()) {
                Integer categoryId = row.getInt("category_id");
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
            String authorSql = " SELECT author_id FROM authors WHERE name= ?";
            Integer authorId = jdbcTemplate.queryForObject(authorSql, Integer.class, author);
            if (authorId != 0 && authorId != null) {
                String bookAuthorSql = " insert into books_authors(author_id, book_id) " +
                        "values ( ?, ? );";
                jdbcTemplate.queryForRowSet(bookAuthorSql, authorId, bookId);

            } else{
                String addCategorySql = " insert into authors(name) "  +
                        "values (?) RETURNING author_id; ";
                authorId = jdbcTemplate.queryForObject(addCategorySql, Integer.class, author);
                String bookCategorySql = " insert into books_author(author_id, book_id) " +
                        "values ( ?, ? );";
                jdbcTemplate.queryForRowSet(bookCategorySql, authorId, bookId);
            }

        }

        return book;
    }
}