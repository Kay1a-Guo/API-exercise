package com.oocl.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private Map<Integer, Company> companyDb = new HashMap<>();

    // 添加公司
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) {
        int id = companyDb.size() + 1;
        company.setId(id);
        companyDb.put(id, company);
    }

    // 获取所有公司（分页）
    @GetMapping
    public List<Company> getCompanies(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        List<Company> companies = new ArrayList<>(companyDb.values());
        int fromIndex = Math.min(page * size, companies.size());
        int toIndex = Math.min(fromIndex + size, companies.size());
        return companies.subList(fromIndex, toIndex);
    }

    // 获取指定公司
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Company company = companyDb.get(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 获取指定公司下的所有员工
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employees>> getEmployeesByCompany(@PathVariable int id) {
        Company company = companyDb.get(id);
        if (company != null) {
            return new ResponseEntity<>(company.getEmployees(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 更新公司名称
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
        Company existingCompany = companyDb.get(id);
        if (existingCompany != null) {
            existingCompany.setName(updatedCompany.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 删除公司
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        if (companyDb.containsKey(id)) {
            companyDb.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 添加员工到公司
    @PostMapping("/{id}/employees")
    public ResponseEntity<Void> addEmployeeToCompany(@PathVariable int id, @RequestBody Employees employee) {
        Company company = companyDb.get(id);
        if (company != null) {
            List<Employees> employees = company.getEmployees();
            int newId = employees.size() + 1;
            employee.setId(newId);
            employees.add(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
