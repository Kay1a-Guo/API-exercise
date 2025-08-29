package com.oocl.training.Integtation;

import com.oocl.training.model.Employees;
import com.oocl.training.repository.employee.EmployeeDbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc

public class EmployeeTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeDbRepository employeeMemoryRepository;

    @BeforeEach
    public void setUp() {
        employeeMemoryRepository.getAllEmployees().clear();
        employeeMemoryRepository.addEmployee(new Employees(1, "John Smith", 32, "MALE", 5000.0));
        employeeMemoryRepository.addEmployee(new Employees(2, "Jane Johnson", 28, "FEMALE", 6000.0));
        employeeMemoryRepository.addEmployee(new Employees(3, "David Williams", 35, "MALE", 5500.0));
        employeeMemoryRepository.addEmployee(new Employees(4, "Emily Brown", 23, "FEMALE", 4500.0));
        employeeMemoryRepository.addEmployee(new Employees(5, "Michael Jones", 40, "MALE", 7000.0));
    }
    @Test
    public void should_return_employees_when_get_all_employees_exist() throws Exception {
        //Given
        List<Employees> givenEmployees = employeeMemoryRepository.getAllEmployees();
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenEmployees.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));

    }
    @Test
    public void should_return_employees_when_get_employees_by_id() throws Exception {
        //Given
        Employees givenEmployees = employeeMemoryRepository.getEmployeeById(1);
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenEmployees.getId()));

    }
    @Test
    public void should_return_employees_when_get_employees_by_page() throws Exception {
        //Given
        List<Employees> givenEmployees = employeeMemoryRepository.getEmployeesByPage(1, 2);
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees?page=1&size=2"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()));

    }
    @Test
    public void should_return_employees_when_get_employees_by_gender() throws Exception {
        //Given
        List<Employees> givenEmployees = employeeMemoryRepository.getEmployeesByGender("MALE");
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees?gender=MALE"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));
    }
    @Test
    public void should_return_employee_when_add_employees_successful() throws Exception {
        //Given
        Employees givenEmployees = employeeMemoryRepository.addEmployee(new Employees(6, "Nick Jones", 30, "MALE", 10000.0));
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType("application/json")
                .content("{\"id\":6,\"name\":\"Nick Jones\",\"age\":30,\"gender\":\"MALE\",\"salary\":10000.0}"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenEmployees.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(givenEmployees.getAge()));
    }
    @Test
    public void should_throw_exception_when_add_employees_failed() throws Exception {
        //Given
        Employees givenEmployees = employeeMemoryRepository.addEmployee(new Employees(6, "Nick Jones", 50, "MALE", 10000.0));
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType("application/json")
                .content("{\"id\":1,\"name\":\"Nick Jones\",\"age\":50,\"gender\":\"MALE\",\"salary\":10000.0}"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void should_delete_employees_successful() throws Exception {
        //Given
        Employees givenEmployees = employeeMemoryRepository.getEmployeeById(1);
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    public void should_throw_exception_when_delete_employees_failed() throws Exception {
        //Given
        Employees givenEmployees = employeeMemoryRepository.getEmployeeById(1);
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1000"));
        //Then
        perform.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void should_put_employees_successful() throws Exception {
        //Given
        Employees updateEmployeeMessage = new Employees(2,"kayla", 20, null, 100000);
        employeeMemoryRepository.updateEmployee(2, updateEmployeeMessage);
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/2")
                .contentType("application/json").content("{\"name\":\"kayla\",\"age\":20,\"salary\":100000}"));

        //Then
        perform.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    public void should_throw_exception_when_put_employees_failed() throws Exception {
        //Given
        Employees updateEmployeeMessage = new Employees(2, "kayla", 20, null, 100000);
        employeeMemoryRepository.updateEmployee(2, updateEmployeeMessage);
        //When
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/2000")
                .contentType("application/json").content("{\"name\":\"kayla\",\"age\":20,\"salary\":100000}"));

        //Then
        perform.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
