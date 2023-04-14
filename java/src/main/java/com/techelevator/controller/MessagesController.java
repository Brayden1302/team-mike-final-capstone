package com.techelevator.controller;


import com.techelevator.dao.MessagesDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class MessagesController {

    @Autowired
    private MessagesDao dao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/message", method = RequestMethod.POST)
    public Messages addMessages(@RequestBody Messages message, Principal user) {
        return dao.addMessages(message, userDao.findIdByUsername(user.getName()));
    }
    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public List<Messages> getMessage(){
        return dao.getMessages();
    }
}
