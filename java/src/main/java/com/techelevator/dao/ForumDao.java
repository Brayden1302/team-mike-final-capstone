package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {

    Forum addForum (Forum forum, int userId);
    List<Forum> getForums();









}
