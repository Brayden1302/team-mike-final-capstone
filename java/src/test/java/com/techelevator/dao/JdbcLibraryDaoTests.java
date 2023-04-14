package com.techelevator.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcLibraryDaoTests extends  BaseDaoTests{

    private JdbcLibraryDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcLibraryDao(dataSource);
    }


    @Test
    public void get_books_should_return_all_books() {




    }




}
