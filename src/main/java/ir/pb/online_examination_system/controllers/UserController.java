package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    private Map<Character, Character> charMap = new HashMap<>();
    @PostMapping("/add")
    public String userSubmit(@ModelAttribute User user, Model model){
//        charMap.p
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
