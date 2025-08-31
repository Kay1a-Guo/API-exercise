package com.oocl.training.controller.dto;

public class TodoResponse {
    private Integer id;
    private String title;

    public TodoResponse(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    public TodoResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
