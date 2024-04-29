package com.zg955.csvimport;

import com.zg955.csvimport.controller.EmployeeReader;
import com.zg955.csvimport.model.Employee;
import com.zg955.csvimport.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeReader employeeReader;

    @Override
    public void run(String... args) {
        log.info("App is online and running !");

        try {
            //employeeReader.readCsvWithBufferedReader();
            //employeeReader.readCsvWithScanner();
            employeeReader.readCsvWithOpenCsv();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //initializeDB();
    }

    private void initializeDB() {
        log.info("Initializing DB");
        Employee employee = new Employee("prénom", "nom", "e@mail.fr");

        employeeService.saveEmployee(employee);
    }
}
