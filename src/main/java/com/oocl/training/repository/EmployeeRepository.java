package com.oocl.training.repository;

import com.oocl.training.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface EmployeeRepository {


    Employees addEmployee(Employees employee) ;

    List<Employees> getAllEmployees();

    Employees getEmployeeById(int id);

    List<Employees> getEmployeesByPage(int page, int size) ;

    List<Employees> getEmployeesByGender(String gender) ;

    void updateEmployee(int id, Employees updatedEmployee) ;
}




