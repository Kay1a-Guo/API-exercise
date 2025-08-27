package com.oocl.training.controller;

public class Todo {
    private int id;
    private String status;
    private String title;
    public Todo(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
    public Todo(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
