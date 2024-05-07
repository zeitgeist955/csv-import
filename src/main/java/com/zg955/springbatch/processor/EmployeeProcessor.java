package com.zg955.springbatch.processor;

import com.zg955.springbatch.model.Employee;
import com.zg955.springbatch.model.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;


public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeEntity> {

    @Override
    public EmployeeEntity process(Employee employee) {
        //log.info("Processing object {}", employee.toString());

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setMail(employee.getMail());

        return employeeEntity;
    }
}
