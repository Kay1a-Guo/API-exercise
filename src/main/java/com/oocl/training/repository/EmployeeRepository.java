package com.oocl.training.repository;

import com.oocl.training.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private final Map<Integer, Employees> employees = new HashMap<>(Map.of(
            1, new Employees(1, "John Smith", 32, "MALE", 5000.0),
            2, new Employees(2, "Jane Johnson", 28, "FEMALE", 6000.0),
            3, new Employees(3, "David Williams", 35, "MALE", 5500.0),
            4, new Employees(4, "Emily Brown", 23, "FEMALE", 4500.0),
            5, new Employees(5, "Michael Jones", 40, "MALE", 7000.0)
    ));

    public Employees addEmployee(Employees employee) {
        int id = employees.size() + 1;
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }


    public List<Employees> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employees getEmployeeById(int id) {
        return employees.get(id);
    }

    public List<Employees> getEmployeesByPage(int page, int size) {
        List<Employees> list = new ArrayList<>(employees.values());
        int fromIndex = Math.min(page * size, list.size());
        int toIndex = Math.min(fromIndex + size, list.size());
        return list.subList(fromIndex, toIndex);
    }

    public List<Employees> getEmployeesByGender(String gender) {
        return employees.values().stream()
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }



    public void updateEmployee(int id, Employees updatedEmployee) {
        updatedEmployee.setId(id);
        employees.put(id, updatedEmployee);
    }
}




