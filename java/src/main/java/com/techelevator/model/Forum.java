package com.techelevator.model;

public class Forum {
    private String forumName;
    private String forumDescription;
    private String username;
    private int forumId;


    public String getUsername() {
        return username;
    }

    public void setUsernname(String usernname) {
        this.username = usernname;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDescription() {
        return forumDescription;
    }

    public void setForumDescription(String forumDescription) {
        this.forumDescription = forumDescription;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
}
