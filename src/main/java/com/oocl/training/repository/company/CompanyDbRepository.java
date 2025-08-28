package com.oocl.training.repository.company;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employees;

import java.util.List;

public class CompanyDbRepository implements CompanyRepository {
    JpaCompanyRepository repository;
    public CompanyDbRepository(JpaCompanyRepository repository) {
        this.repository = repository;
    }
    @Override
    public Company addCompany(Company company) {
        return repository.save(company);
    }

    @Override
    public List<Company> getCompanies(int page, int size) {
        return repository.findAll().subList((page-1)*size,page*size);
    }

    @Override
    public Company getCompanyById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employees> getEmployeesByCompany(int id) {
        return repository.findById(id).orElse(null).getEmployees();
    }

    @Override
    public void updateCompany(int id, Company updatedCompany) {
        updatedCompany.setId(id);
        repository.save(updatedCompany);
    }

    @Override
    public void deleteCompany(int id) {
        repository.deleteById(id);

    }

    @Override
    public void addEmployeeToCompany(int id, Employees employee) {

    }
}
