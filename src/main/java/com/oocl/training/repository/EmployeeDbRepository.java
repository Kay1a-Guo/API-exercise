package com.oocl.training.repository;

import com.oocl.training.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDbRepository implements EmployeeRepository {
    JpaEmployeeRepository repository;
    public EmployeeDbRepository(JpaEmployeeRepository repository) {
        this.repository = repository;
    }
    @Override
    public Employees addEmployee(Employees employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employees> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employees getEmployeeById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employees> getEmployeesByPage(int page, int size) {
        return repository.findAll().subList((page-1)*size,page*size);
    }

    @Override
    public List<Employees> getEmployeesByGender(String gender) {
        return repository.getEmployeeByGender(gender);
    }

    @Override
    public void updateEmployee(int id, Employees updatedEmployee) {
        repository.save(updatedEmployee);
    }
}
