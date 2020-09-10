package com.eman.securEman.controller;

import com.eman.securEman.db.UserRepository;
import com.eman.securEman.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Mohammad on 8/6/2020.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("login")
    public String login(){
        return "login";
    }



    @GetMapping("insert")
    public String insert(){
        User eman=new User("eman","eman","USER","");
        this.userRepository.save(eman);
        return "index";
    }
}
