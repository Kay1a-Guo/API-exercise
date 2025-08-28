package com.oocl.training.service;


import com.oocl.training.exception.InvalidEmployeeException;
import com.oocl.training.model.Employees;
import com.oocl.training.repository.EmployeeMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record EmployeeService(EmployeeMemoryRepository employeeMemoryRepository) {

    public Employees addEmployee(Employees employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65)
            throw new InvalidEmployeeException("Employee age must be between 18 and 65.");
        if (employee.getAge() > 30 && employee.getSalary() < 20000.0)
            throw new InvalidEmployeeException("Employee older than 30 must have a salary of at least 20000.");
        employeeMemoryRepository.addEmployee(employee);

        return employee;
    }

    public List<Employees> getAllEmployees() {
        return employeeMemoryRepository.getAllEmployees();
    }

    public Employees getEmployeeById(int id) {
        return employeeMemoryRepository.getEmployeeById(id);
    }

    public List<Employees> getEmployeesByPage(int page, int size) {
        return employeeMemoryRepository.getEmployeesByPage(page, size);
    }

    public List<Employees> getEmployeesByGender(String gender) {
        return employeeMemoryRepository.getEmployeesByGender(gender);
    }

    public void deleteEmployee(int id) {
        Employees emp = employeeMemoryRepository.getEmployeeById(id);
        if (emp == null || !emp.isActive()) {
            throw new InvalidEmployeeException("Employee with id " + id + " does not exist.");
        } else {
            emp.setActive(false);
            employeeMemoryRepository.updateEmployee(id, emp);
        }
    }

    public void updateEmployee(int id, Employees updatedEmployee) {
        Employees emp = employeeMemoryRepository.getEmployeeById(id);
        if (emp == null || !emp.isActive()) {
            throw new InvalidEmployeeException("Employee with id " + id + " does not exist.");

        } else {
            employeeMemoryRepository.updateEmployee(id, updatedEmployee);
        }
    }

}






