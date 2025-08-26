package com.oocl.training.controller;

import java.util.List;

public class Company {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    private String name;
    private List<Employees> employees;

    public Company(int id, String name, List<Employees> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }
}
