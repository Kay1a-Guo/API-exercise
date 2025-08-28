package com.oocl.training.repository.employee;

import com.oocl.training.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employees,Integer> {
    List<Employees> getEmployeeByGender(String gender);

}
