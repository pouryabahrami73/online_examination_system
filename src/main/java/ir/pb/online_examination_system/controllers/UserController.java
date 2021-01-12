package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
// UserController class returns the views that should be returned to the user with no specific
// role or user who is not authenticated.
@Controller
@RequestMapping("/user")
public class UserController {
    // user service autowired to give the needed services to the UserController class.
    @Autowired
    private UserService service;
    // charMap field is a map of persian characters to the english characters.
//    private Map<Character, Character> charMap = new HashMap<>();
    // userSubmit method gets the user's data which were written in the registration form.
    @PostMapping("/add")
    public String userSubmit(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);
        String defaultUserName = user.getFirstName().concat(String.valueOf(user.getNationalCode()));
        long defaultPassWord = user.getNationalCode();
        user.setUserName(defaultUserName);
        user.setPassWord(service.hashPass(String.valueOf(defaultPassWord)));
        service.save(user);
        return "sign-up-res";
    }
    @GetMapping("/not-registered")
    public String showNotRegisteredPage(){
        return "not-registered-yet";
    }

}
