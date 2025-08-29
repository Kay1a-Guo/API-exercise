package com.oocl.training.controller;

import com.oocl.training.controller.dto.EmployeeRequest;
import com.oocl.training.controller.dto.EmployeeResponse;
import com.oocl.training.controller.mapper.EmployeeMapper;
import com.oocl.training.model.Employees;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.oocl.training.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {
    private final EmployeeMapper employeeMapper ;
    private final EmployeeService employeeService;
    public EmployeesController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest request) throws IllegalAccessException {
        //return employeeService.addEmployee(employee);
        Employees employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeService.addEmployee(employee));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getEmployees(@RequestParam(required = false) String gender,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size) {
        if (gender != null) {
            //return employeeService.getEmployeesByGender(gender);
            return employeeMapper.toResponseList(employeeService.getEmployeesByGender(gender));
        } else if (page != null && size != null) {
            //return employeeService.getEmployeesByPage(page, size);
            return employeeMapper.toResponseList(employeeService.getEmployeesByPage(page, size));
        } else {
            //return employeeService.getAllEmployees();
            return employeeMapper.toResponseList(employeeService.getAllEmployees());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployeeById(@PathVariable int id) {
        //return employeeService.getEmployeeById(id);
        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest updatedEmployee) {
        //employeeService.updateEmployee(id, updatedEmployee);
        Employees employee = employeeMapper.toEntity(updatedEmployee);
        employeeService.updateEmployee(id, employee);
    }


}
