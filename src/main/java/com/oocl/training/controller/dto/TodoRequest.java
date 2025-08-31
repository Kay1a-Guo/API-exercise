package com.oocl.training.controller.dto;

public class TodoRequest {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TodoRequest(String title) {
        this.title = title;
    }

    public TodoRequest() {
    }

}
