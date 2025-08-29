package com.oocl.training.controller;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employees;
import com.oocl.training.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/companies")
public record CompanyController(CompanyService companyService) {


    // 添加公司
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    // 获取所有公司（分页）
    @GetMapping
    public List<Company> getCompanies(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return companyService.getCompanies(page, size);
    }

    // 获取指定公司
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    // 获取指定公司下的所有员工
    @GetMapping("/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employees> getEmployeesByCompany(@PathVariable int id) {
        return companyService.getEmployeesByCompany(id);
    }

    // 更新公司名称
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
        companyService.updateCompany(id, updatedCompany);
    }

    // 删除公司
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }

    // 添加员工到公司
//    @PostMapping("/{id}/employees")
//    public void addEmployeeToCompany(@PathVariable int id, @RequestBody Employees employee) {
//        companyService.addEmployeeToCompany(id, employee);
//    }
}
