package com.oocl.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeesController {
    private Map<Integer, Employees> db = new HashMap<>();

    // 添加员工
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employees employee) {
        int id = db.size() + 1;
        employee.setId(id);
        db.put(db.size() + 1,employee);
    }
    //获取所有员工
    @GetMapping("/employees")
    public List<Employees> getEmployees() {
        List<Employees> employee = new ArrayList<>(db.values());
        return employee;
    }

    //获取所有员工（按id）
    @GetMapping("/employees/{id}")
    public Employees getEmployeeById(@PathVariable int id) {
        Employees employee = db.get(id);
        return employee;
    }

    //获取所有员工(分页)
    @GetMapping("/employees?page={page}&size={size}")
    public List<Employees> getAllEmployees(@RequestParam int page, @RequestParam int size) {
        List<Employees> employee = new ArrayList<>(db.values());
        int fromIndex = Math.min(page * size, employee.size());
        int toIndex = Math.min(fromIndex + size, employee.size());
        return employee.subList(fromIndex, toIndex);
    }

    //获取所有男性员工
    @GetMapping("/employees?gender=male")
    public List<Employees> getEmployeeByGender(){
        List<Employees> result = new ArrayList<>();
        for (Employees employee : db.values()) {
            if (employee.getGender().equals("male")) {
                result.add(employee);
            }
        }
        return result;
    }

    //删除员工
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //更新员工信息
    @PutMapping("/employees/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable int id, @RequestBody Employees updatedEmployee) {
        if (db.containsKey(id)) {
            updatedEmployee.setId(id);
            db.put(id, updatedEmployee);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}
