package com.eman.securEman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mohammad on 8/6/2020.
 */
@Controller
@RequestMapping("profile")
public class ProfileController {
    @GetMapping("index")
    public String index(){
        return "profile/index";
    }
}
