package com.oocl.training.service;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employees;
import com.oocl.training.repository.company.CompanyDbRepository;
import com.oocl.training.repository.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyDbRepository companyDbRepository) {
        this.companyRepository = companyDbRepository;
    }

    @Autowired
    private EmployeeService employeeService;





    public void addCompany(@RequestBody Company company){
        companyRepository.addCompany(company);

    }

    public List<Company> getCompanies(int page, int size){

        return companyRepository.getCompanies(page, size);
    }

    public Company getCompanyById(int id){
        return companyRepository.getCompanyById(id);
    }

    public List<Employees> getEmployeesByCompany (int id){

        if (employeeService.getEmployeesByCompany(id) == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }
        return employeeService.getEmployeesByCompany(id);
    }

    public void updateCompany (int id, Company updatedCompany){

        if (companyRepository.getCompanyById(id) == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }
        companyRepository.updateCompany(id, updatedCompany);
    }

    public void deleteCompany (int id){

        companyRepository.deleteCompany(id);
    }

//     public void addEmployeeToCompany(int id, Employees employee) {
//
//         if (companyRepository.getCompanyById(id) == null) {
//             throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
//         }
//         companyRepository.addEmployeeToCompany(id, employee);
//     }

}
