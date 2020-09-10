package com.eman.securEman.db;

import com.eman.securEman.model.Employee;
import com.eman.securEman.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohammad on 8/8/2020.
 */
@Service
public class DbInt implements CommandLineRunner {
    UserRepository userRepository;
    EmployeeRepository employeeRepository;
    PasswordEncoder passwordEncoder;
   @Autowired
    public DbInt(UserRepository userRepository,EmployeeRepository repository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository=repository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {
       userRepository.deleteAll();
        User eman=new User("eman",passwordEncoder.encode("eman"),"USER","");
//        this.userRepository.save(eman)
       // Employee eman=new Employee("eman","eman");
        //this.employeeRepository.save(eman);
        User admin=new User("admin",passwordEncoder.encode("admin"),"ADMIN",
                "ACCESS_TEST1"+","+"ACCESS_TEST2");
        User manager=new User("manager",passwordEncoder.encode("manager"),"MANAGER","ACCESS_TEST1");

        List<User> users= Arrays.asList(eman,admin,manager);
        this.userRepository.saveAll(users);
    }
}
