package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.MessagesDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
public class ForumController {

    @Autowired
    private ForumDao dao;
    @Autowired
    private MessagesDao messageDao;
    @Autowired
    private UserDao userDao;


    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/forum", method = RequestMethod.POST)
    public Forum addForum(@RequestBody Forum forum, Principal user) {
        Forum addedForum = dao.addForum(forum, userDao.findIdByUsername(user.getName()));
        addedForum.setUsernname(user.getName());
        return addedForum;
    }

    @RequestMapping(path = "/forum", method = RequestMethod.GET)
    public List<Forum> getForum() {
        return dao.getForums();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/forum/{id}", method = RequestMethod.POST)
    public Messages addMessages(@RequestBody Messages message, Principal user, @PathVariable int id) {
        Messages addedMessage = messageDao.addMessages(message, userDao.findIdByUsername(user.getName()), id);
        addedMessage.setUsername(user.getName());
        return addedMessage;
    }
    @RequestMapping(path = "/forum/{id}", method = RequestMethod.GET)
    public List<Messages> getMessage(@PathVariable int id){
        return messageDao.getMessages(id);
    }


}
