package ir.pb.online_examination_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// LoginController class returns the very first pages of the app which are the login page and the sign-up-page.
@Controller
@RequestMapping("/")
public class LoginController {
    // login method returns the login page for any requests from the non login user.
    @GetMapping(value = {"/", "/login"})
    public String login(){
        return "login";
    }
    // signUpForm method returns the sign-up page for users who want to register in the system.
    @RequestMapping("/sign-up-form")
    public String signUpForm(){
        return "sign-up";
    }
}
