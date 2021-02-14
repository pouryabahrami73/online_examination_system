package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.dtos.UserDTO;
import ir.pb.online_examination_system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// UserController class returns the views that should be returned to the user with no specific
// role or user who is not authenticated.
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    // user service autowired to give the needed services to the UserController class.
    @Autowired
    private UserService service;
    // userSubmit method gets the user's data which were written in the registration form.
    /*public String userSubmit(User user, Model model){
        model.addAttribute("user", user);
        String userNameEnteredByUser = user.getUserName();
        long defaultPassWord = user.getNationalCode();
        user.setUserName(userNameEnteredByUser);
        user.setPassWord(service.hashPass(String.valueOf(defaultPassWord)));
        service.save(user);
        return "sign-up-res";
    }*/
    // showNotRegisteredPage method returns not-registered-yet view which shows to the user that the admin hasn't
    // confirmed your registration request.
    @GetMapping("/not-registered")
    public String showNotRegisteredPage(){
        return "not-registered-yet";
    }

    @PostMapping("/check")
    public String addUser(@ModelAttribute @Valid UserDTO dto, Model model){
        User user = modelMapper.map(dto, User.class);
        if (service.findByNationalCode(user.getNationalCode()) == null &
                service.findByUserName(user.getUserName()) == null) {
            model.addAttribute("user", user);
            String userNameEnteredByUser = user.getUserName();
            long defaultPassWord = user.getNationalCode();
            user.setUserName(userNameEnteredByUser);
            user.setPassWord(service.hashPass(String.valueOf(defaultPassWord)));
            service.save(user);
        }else {
            model.addAttribute("message", "نام کاربری یا کد ملی شما قبلا ثبت شده!");
        }
        return "sign-up-res";
    }

    /*private String resendSignUpPage(User user, Model model) {
        model.addAttribute("message", "کاربری با این نام کاربری یا کد ملی وجود دارد!");
        model.addAttribute("");
        return "sign-up";
    }*/

}
