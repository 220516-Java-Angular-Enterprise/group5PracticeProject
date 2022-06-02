package com.revature.revit.models;

public class Comments {

    //Double check with CommentDAO
    //id, thread_id, user_id,
    private String id;
    private String thread_id;
    private String user_id;
    private String postContent;

    private String time;

    public Comments() { super(); }


    public Comments(String id, String thread_id, String user_id, String postContent, String time) {
        this.id = id;
        this.thread_id = thread_id;
        this.user_id = user_id;
        this.postContent = postContent;
        this.time = time;
    }


    //<editor-fold desc="Get/Set">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    //</editor-fold>

}
