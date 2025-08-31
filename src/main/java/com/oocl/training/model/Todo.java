package com.oocl.training.model;

public class Todo {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private boolean status;
    private String title;

    public Todo(){

    }


    public Todo(int id, boolean status, String title) {
        this.id = id;
        this.status = true;
        this.title = title;
    }
}
