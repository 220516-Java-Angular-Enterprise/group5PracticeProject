package com.revature.revit.models;

public class Thread {

    private String id;
    private String title;
    private String user_id;

//<editor-fold desc="constructors">
    public Thread (String id, String title, String user_id){
        this.id=id;
        this.title=title;
        this.user_id=user_id;
    }
    public Thread (){ super();}

// <editor-fold desc="constructors">

    //<editor-fold desc="Get/Set">

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    //</editor-fold desc="Get/Set">

}
