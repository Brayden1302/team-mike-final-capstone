package com.techelevator.model;

public class Messages {
    private int messageId;
    private int forumId;
    private String username;
    private String messageContent;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
