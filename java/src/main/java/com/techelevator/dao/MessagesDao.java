package com.techelevator.dao;

import com.techelevator.model.Messages;

import java.util.List;

public interface MessagesDao {

    Messages addMessages (Messages messages, int userId);
    List<Messages> getMessages();
}
