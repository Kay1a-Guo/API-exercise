package com.oocl.training.service;

import com.oocl.training.exception.InvalidEmployeeException;
import com.oocl.training.model.Employees;
import com.oocl.training.repository.employee.EmployeeDbRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDbRepository employeeMemoryRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void add_employee_successful() {
        //Given
        Employees employee = new Employees(1,"oocl",22,"MALE",12000.0);
        Employees mockedEmployee = new Employees(1, employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary());
        Mockito.when(employeeMemoryRepository.addEmployee(Mockito.any(Employees.class))).thenReturn(employee);
        //When
        Employees addEmployee = employeeService.addEmployee(employee);
        //Then
        assertEquals(addEmployee.getId(), mockedEmployee.getId());
        assertEquals(addEmployee.getName(), mockedEmployee.getName());
        assertEquals(addEmployee.getAge(), mockedEmployee.getAge());
    }

//    @Test
//    void throw_exception_when_given_age_below_18() {
//        //Given
//        Employees employee = new Employees(1,"oocl",12,"MALE",12000.0);
//        //When & Then
//        InvalidEmployeeException Exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
//        assertEquals("Employee age must be between 18 and 65.", Exception.getMessage());
//    }
//
//    @Test
//    void throw_exception_when_given_age_over_65() {
//        //Given
//        Employees employee = new Employees(1,"oocl",80,"MALE",3000000.0);
//        //When & Then
//        InvalidEmployeeException Exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
//        assertEquals("Employee age must be between 18 and 65.", Exception.getMessage());
//    }

    @Test
    void throw_exception_when_given_age_not_match_salary() {
        //Given
        Employees employee = new Employees(1,"oocl",40,"MALE",12000.0);
        //When & Then
        InvalidEmployeeException Exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(employee));
        assertEquals("Employee older than 30 must have a salary of at least 20000.", Exception.getMessage());
    }

    @Test
    void get_all_employees() {
        //Given
        Map<Integer, Employees> employees = new HashMap<>(Map.of(
                1, new Employees(1, "John Smith", 32, "MALE", 5000.0),
                2, new Employees(2, "Jane Johnson", 28, "FEMALE", 6000.0)));
        Mockito.when(employeeMemoryRepository.getAllEmployees()).thenReturn(employees.values().stream().toList());
        //When
        List<Employees> result = employeeService.getAllEmployees();
        //Then
        assertEquals(2, result.size());
        assertEquals("John Smith", result.get(0).getName());
    }
    @Test
    void get_employee_by_id(){
        //Given
        Map<Integer, Employees> employees = new HashMap<>(Map.of(
                1, new Employees(1, "John Smith", 32, "MALE", 5000.0),
                2, new Employees(2, "Jane Johnson", 28, "FEMALE", 6000.0)));
        Mockito.when(employeeMemoryRepository.getEmployeeById(1)).thenReturn(employees.get(1));
        //When

        Employees result = employeeService.getEmployeeById(1);
        //Then
        assertEquals("John Smith", result.getName());

    }
    @Test
    void get_employee_by_page(){
        //Given
        Map<Integer, Employees> employees = new HashMap<>(Map.of(
                1, new Employees(1, "John Smith", 32, "MALE", 5000.0),
                2, new Employees(2, "Jane Johnson", 28, "FEMALE", 6000.0),
                3, new Employees(3, "David Williams", 35, "MALE", 5500.0),
                4, new Employees(4, "Emily Brown", 23, "FEMALE", 4500.0),
                5, new Employees(5, "Michael Jones", 40, "MALE", 7000.0)
        ));
        Mockito.when(employeeMemoryRepository.getEmployeesByPage(2,2)).thenReturn(List.of(employees.get(3),employees.get(4)));
        //When
        List<Employees> result = employeeService.getEmployeesByPage(2,2);
        //Then
        assertEquals(2, result.size());
        assertEquals("David Williams", result.get(0).getName());
    }
    @Test
    void get_employee_by_gender(){
        //Given
        List<Employees> employees = List.of(
                new Employees(1, "John Smith", 32, "MALE", 5000.0),
                new Employees(3, "David Williams", 35, "MALE", 5500.0),
                new Employees(5, "Michael Jones", 40, "MALE", 7000.0)
        );

        Mockito.when(employeeMemoryRepository.getEmployeesByGender("MALE")).thenReturn(employees);
        //When
        List<Employees> result = employeeService.getEmployeesByGender("MALE");
        //Then
        assertEquals(3, result.size());
        assertEquals("John Smith", result.get(0).getName());
    }
    @Test
    void delete_employee_successfully(){
        //Given
        Employees employee = new Employees(1,"oocl",22,"MALE",12000.0);
        Mockito.when(employeeMemoryRepository.getEmployeeById(1)).thenReturn(employee);
        //When
        employeeService.deleteEmployee(1);
        //Then
        assertFalse(employee.isActive());
        Mockito.verify(employeeMemoryRepository,Mockito.times(1)).getEmployeeById(1);
        Mockito.verify(employeeMemoryRepository,Mockito.times(1)).updateEmployee(1,employee);

    }
    @Test
    void throw_exception_when_delete_employee_not_exist(){
        //Given
        Mockito.when(employeeMemoryRepository.getEmployeeById(1)).thenReturn(null);
        //When & Then
        InvalidEmployeeException Exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.deleteEmployee(1));
        assertEquals("Employee with id 1 does not exist.", Exception.getMessage());
    }
    @Test
    void update_employee_successfully(){
        //Given
        Employees old_employee = new Employees(1,"oocl",22,"MALE",12000.0);
        Employees update_message = new Employees(1,null,80,null,13000.0);
        Mockito.when(employeeMemoryRepository.getEmployeeById(1)).thenReturn(old_employee);
        //When
        employeeService.updateEmployee(1,update_message);
        //Then
        Mockito.verify(employeeMemoryRepository,Mockito.times(1)).getEmployeeById(1);
        Mockito.verify(employeeMemoryRepository,Mockito.times(1)).updateEmployee(1,update_message);

    }




}