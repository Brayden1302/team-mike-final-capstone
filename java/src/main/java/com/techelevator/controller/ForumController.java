package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class ForumController {

    @Autowired
    private ForumDao dao;
    @Autowired
    private UserDao userDao;



    @RequestMapping(path = "/forum", method = RequestMethod.POST)
    public Forum addForum(@RequestBody Forum forum, Principal user) {
        return dao.addForum(forum, userDao.findIdByUsername(user.getName()));
    }

    @RequestMapping(path = "/forum", method = RequestMethod.GET)
    public List<Forum> getForum() {
        return dao.getForums();
    }


}
