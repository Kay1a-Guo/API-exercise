package com.oocl.training.controller;

import com.oocl.training.model.Employees;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.oocl.training.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public record EmployeesController(EmployeeService employeeService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employees addEmployee(@RequestBody Employees employee) throws IllegalAccessException {
        return employeeService.addEmployee(employee);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employees> getEmployees(@RequestParam(required = false) String gender,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size) {
        if (gender != null) {
            return employeeService.getEmployeesByGender(gender);
        } else if (page != null && size != null) {
            return employeeService.getEmployeesByPage(page, size);
        } else {
            return employeeService.getAllEmployees();
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employees getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable int id, @RequestBody Employees updatedEmployee) {
        employeeService.updateEmployee(id, updatedEmployee);
    }

    @GetMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public List<Employees> getEmployeesByCompany(@PathVariable int id) {
        return employeeService.getEmployeesByCompany(id);
    }
}
