package com.zg955.csvimport.service;

import com.zg955.csvimport.model.Employee;
import com.zg955.csvimport.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveRecipe(Employee employee) {
        return employeeRepository.save(employee);
    }
}
