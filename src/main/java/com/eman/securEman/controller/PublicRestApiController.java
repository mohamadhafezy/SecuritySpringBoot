package com.eman.securEman.controller;

import com.eman.securEman.db.UserRepository;
import com.eman.securEman.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mohammad on 8/6/2020.
 */
@RestController
@RequestMapping("public/api")
public class PublicRestApiController {
    private UserRepository userRepository;

    public PublicRestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("test1")
    public String test1(){
        return "Api test is test1";
    }
    @GetMapping("test2")
    public String test2(){
        return "Api test is test2";
    }
    @GetMapping("users")
    public List<User> allUsers(){
     return (List<User>) this.userRepository.findAll();
    }
}
