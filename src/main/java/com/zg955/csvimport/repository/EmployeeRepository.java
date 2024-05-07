package com.zg955.csvimport.repository;

import com.zg955.csvimport.model.OldEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<OldEmployee, Long> {
}
