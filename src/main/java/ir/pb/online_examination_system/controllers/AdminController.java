package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;
// returns main page of admin
    @GetMapping
    public String getAdmin() {
        return "admin";
    }
// prepares new course page
    @GetMapping("/create-new-course")
    public String prepareNewCoursePage(Model model){
        model.addAttribute("masters", service.masters());
        model.addAttribute("students", service.students());
        return "new-course";
    }
// makes new course
    @PostMapping("/create-new-course")
    public String createNewCourse(@ModelAttribute Optional<Course> optionalCourse){
        Course course = optionalCourse.get();
        if (course.getStartDate().before(course.getFinishDate())){
            service.saveCourse(course);
            return "course-saved-success";
        }
        return "admin";
    }
// prepares users registration page
    @GetMapping("/register-newly-added")
    public String prepareRegistrationPage(){
        return "register-new-users";
    }
// shows newly added users based on filtration
    @PostMapping("/register-newly-added")
    public String showNewlyAdded(@ModelAttribute Optional<User> filterUser, Model model){
        String filterFirstName = filterUser.get().getFirstName();
        String filterLastName = filterUser.get().getLastName();
        String filterRoles = filterUser.get().getRoles();
        model.addAttribute("users", service.filteredUsers(filterFirstName, filterLastName, filterRoles));
        return "register-new-users";
    }
// activate registered user and makes student or master based on the user's role
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Optional<User> userToBeRegistered, Model model){
        User user = service.findByUserId(userToBeRegistered.get().getId());
        model.addAttribute("userToBeRegistered", userToBeRegistered);
        user.setActive(true);
        service.saveUser(user);
        if (user.getRoles().equals("ROLE_MASTER")){
            service.saveMaster(user);
        }else if (user.getRoles().equals("ROLE_STUDENT")){
            service.saveStudent(user);
        }
        return "register-new-users";
    }
}
