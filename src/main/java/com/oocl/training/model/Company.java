package com.oocl.training.model;

import com.oocl.training.model.Employees;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "company")

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Company(Integer companyId) {
        this.id = companyId;
    }
    //private List<Employees> employees;


    public Integer getId() {
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

//    //public List<Employees> getEmployees() {
//        return employees;
//    }

//    public void setEmployees(List<Employees> employees) {
//        this.employees = employees;
//    }
//
//
//    public Company(int id, String name, List<Employees> employees) {
//        this.id = id;
//        this.name = name;
//        this.employees = employees;
//    }

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Company(){

    }
}
