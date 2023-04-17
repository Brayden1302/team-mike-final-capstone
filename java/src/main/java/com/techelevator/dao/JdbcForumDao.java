package com.techelevator.dao;

import com.techelevator.model.Forum;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
public class JdbcForumDao implements ForumDao{

    private JdbcTemplate jdbcTemplate;

    private JdbcForumDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Forum addForum(Forum forum, int userId) {
        Integer forumId = null;
        String forumSql ="INSERT INTO forum (forum_title, user_id, forum_description) " +
                "VALUES (?, ?, ?) RETURNING forum_id;";
        forumId = jdbcTemplate.queryForObject(forumSql, Integer.class, forum.getForumName(), userId, forum.getForumDescription());
        forum.setForumId(forumId);

        return forum;
    }

    @Override
    public List<Forum> getForums() {
        List<Forum> forums = new ArrayList<>();
        String sql = " SELECT forum_title, users.username, forum_description, forum_id " +
                "FROM forum " +
                "JOIN users ON forum.user_id = users.user_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while ( results.next()) {
            Forum forum = mapRowToForum(results);
            forums.add(forum);
        }
        return forums;
    }

    private Forum mapRowToForum(SqlRowSet results) {
        Forum forum = new Forum();
        forum.setForumDescription(results.getString("forum_description"));
        forum.setForumId(results.getInt("forum_id"));
        forum.setUsernname(results.getString("username"));
        forum.setForumName(results.getString("forum_title"));
        return forum;
    }


}
