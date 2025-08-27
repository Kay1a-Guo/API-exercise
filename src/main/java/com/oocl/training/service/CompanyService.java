package com.oocl.training.service;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employees;
import com.oocl.training.repository.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CompanyService {
    public CompanyService(CompanyRepository repository) {
    }

    public void addCompany(@RequestBody Company company){
        CompanyRepository repository = new CompanyRepository();
        repository.addCompany(company);
    }

    public List<Company> getCompanies(int page, int size){
        CompanyRepository repository = new CompanyRepository();
        return repository.getCompanies(page, size);
    }

    public Company getCompanyById(int id){
        CompanyRepository repository = new CompanyRepository();
        return repository.getCompanyById(id);
    }

    public List<Employees> getEmployeesByCompany (int id){
        CompanyRepository repository = new CompanyRepository();
        if (repository.getCompanyById(id) == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }
        return repository.getEmployeesByCompany(id);
    }

    public void updateCompany (int id, Company updatedCompany){
        CompanyRepository repository = new CompanyRepository();
        if (repository.getCompanyById(id) == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }
        repository.updateCompany(id, updatedCompany);
    }

    public void deleteCompany (int id){
        CompanyRepository repository = new CompanyRepository();
        repository.deleteCompany(id);
    }

    public void addEmployeeToCompany(int id, Employees employee) {
        CompanyRepository repository = new CompanyRepository();
        if (repository.getCompanyById(id) == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }
        repository.addEmployeeToCompany(id, employee);
    }

}
