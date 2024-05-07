package com.zg955.csvimport.service;

import com.zg955.csvimport.model.OldEmployee;
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
        List<OldEmployee> oldEmployees = new ArrayList<>();

        csvData.forEach(data -> {
            log.info("Converting data {} into employee", data);
            //FIXME this can break if an entry hasnt have all of the properties
            OldEmployee newOldEmployee = new OldEmployee(data.get(0), data.get(1), data.get(2));
            log.trace("New employee from CSV : {}", newOldEmployee);
            oldEmployees.add(newOldEmployee);
        });

        log.info("New list with {} entry to store", oldEmployees.size());
        saveAllEmployees(oldEmployees);
    }

    public Iterable<OldEmployee> saveAllEmployees(Iterable<OldEmployee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public OldEmployee saveEmployee(OldEmployee oldEmployee) {
        return employeeRepository.save(oldEmployee);
    }
}
