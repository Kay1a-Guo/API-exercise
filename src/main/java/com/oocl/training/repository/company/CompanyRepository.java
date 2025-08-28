package com.oocl.training.repository.company;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employees;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CompanyRepository {
    Company addCompany(Company company) ;

    List<Company> getCompanies (int page, int size);

    Company getCompanyById (int id);

    List<Employees> getEmployeesByCompany (int id) ;

    void updateCompany (int id, Company updatedCompany);

    void deleteCompany (int id);

    //void addEmployeeToCompany(int id, Employees employee) ;



    }



