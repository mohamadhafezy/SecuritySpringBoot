package com.eman.securEman.db;

import com.eman.securEman.model.Employee;
import com.eman.securEman.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mohammad on 8/8/2020.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
   User findUserByUsername(String username);
}
