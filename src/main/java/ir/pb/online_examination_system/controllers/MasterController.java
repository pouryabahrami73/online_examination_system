package ir.pb.online_examination_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/master")
public class MasterController {

    @GetMapping
    public String getMaster() {
        return "master";
    }

    @GetMapping("/my-courses")
    public String showMyCourses(Model model){
        return "";
    }
}
