package com.zg955.csvimport.service;

import com.zg955.csvimport.model.Employee;
import com.zg955.csvimport.repository.EmployeeRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void handleCsvValues(List<List<String>> csvData) {
        List<Employee> employees = new ArrayList<>();

        csvData.forEach(data -> {
            log.info("Converting data {} into employee", data);
            //FIXME this can break if an entry hasnt have all of the properties
            Employee newEmployee = new Employee(data.get(0), data.get(1), data.get(2));
            log.trace("New employee from CSV : {}", newEmployee);
            employees.add(newEmployee);
        });

        log.info("New list with {} entry to store", employees.size());
        saveAllEmployees(employees);
    }

    public Iterable<Employee> saveAllEmployees(Iterable<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
