package com.oocl.training.repository;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employees;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CompanyRepository {
    private final HashMap<Integer, Company> companyDb = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employees(1, "John Smith", 32, "MALE", 5000.0),
                    new Employees(2, "Jane Johnson", 28, "FEMALE", 6000.0)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employees(3, "David Williams", 35, "MALE", 5500.0),
                    new Employees(4, "Emily Brown", 23, "FEMALE", 4500.0),
                    new Employees(5, "Michael Jones", 40, "MALE", 7000.0)
            )),
            3, new Company(3, "Global Innovators"),
            4, new Company(4, "Stellar Enterprises"),
            5, new Company(5, "Nexus Industries")
    ));
    public void addCompany(@RequestBody Company company) {
        int id = companyDb.size() + 1;
        company.setId(id);
        companyDb.put(id, company);
    }
    public List<Company> getCompanies (int page, int size) {
        List<Company> companies = List.copyOf(companyDb.values());
        int fromIndex = Math.min(page * size, companies.size());
        int toIndex = Math.min(fromIndex + size, companies.size());
        return companies.subList(fromIndex, toIndex);
    }

    public Company getCompanyById (int id) {
        return companyDb.get(id);
    }

    public List<Employees> getEmployeesByCompany (int id) {
        Company company = companyDb.get(id);
        return company.getEmployees();
    }

    public void updateCompany (int id, Company updatedCompany) {
        Company existingCompany = companyDb.get(id);
        updatedCompany.setId(id);
        companyDb.put(id, updatedCompany);

    }

    public void deleteCompany (int id) {
        companyDb.remove(id);
    }

    public void addEmployeeToCompany(int id, Employees employee) {
        Company company = companyDb.get(id);
        List<Employees> employees = company.getEmployees();
        employees.add(employee);
        }
    }



