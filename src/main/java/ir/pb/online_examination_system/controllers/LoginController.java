package ir.pb.online_examination_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public String login(){
        return "login";
    }

    @RequestMapping("/sign-up-form")
    public String signUpForm(){
        return "sign-up";
    }
}
