package com.oocl.training.service;


import com.oocl.training.exception.InvalidEmployeeException;
import com.oocl.training.model.Employees;
import com.oocl.training.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record EmployeeService(EmployeeRepository employeeRepository) {

    public void addEmployee(Employees employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65)
            throw new InvalidEmployeeException("Employee age must be between 18 and 65.");
        if (employee.getAge() > 30 && employee.getSalary() < 20000.0)
            throw new InvalidEmployeeException("Employee older than 30 must have a salary of at least 20000.");
        employeeRepository.addEmployee(employee);

    }

    public List<Employees> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employees getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employees> getEmployeesByPage(int page, int size) {
        return employeeRepository.getEmployeesByPage(page, size);
    }

    public List<Employees> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployeesByGender(gender);
    }

    public void deleteEmployee(int id) {
        Employees emp = employeeRepository.getEmployeeById(id);
        if (emp == null || !emp.isActive()) {
            throw new InvalidEmployeeException("Employee with id " + id + " does not exist.");
        } else {
            emp.setActive(false);
            employeeRepository.updateEmployee(id, emp);

        }
    }

    public void updateEmployee(int id, Employees updatedEmployee) {
        Employees emp = employeeRepository.getEmployeeById(id);
        if (emp == null || !emp.isActive()) {
            throw new InvalidEmployeeException("Employee with id " + id + " does not exist.");

        } else {
            employeeRepository.updateEmployee(id, updatedEmployee);

        }
    }

}






