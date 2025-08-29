package com.oocl.training.controller.mapper;

import com.oocl.training.controller.dto.EmployeeResponse;
import com.oocl.training.model.Employees;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employees employee){
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee, response);
        return response;
    }

    public List<EmployeeResponse> toResponseList(List<Employees> employees){
        return employees.stream().map(this::toResponse).toList();
    }
}
