package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService service;

    @GetMapping
    public String getMaster(Model model) {
        model.addAttribute("courses", service.findMyCourses());
        return "master";
    }

    @PostMapping("/course-exams")
    public String showMyCourses(@ModelAttribute Optional<Course> optionalCourse, Model model) {
        long id = optionalCourse.get().getId();
        Course course = service.findCourseById(id);
        model.addAttribute("courseName", course.getName());
        List<Exam> exams = service.findExamsOfCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("exams", exams);
        return "exams-of-course";
    }

    @PostMapping(value = "/exam-operation", params = "action=delete")
    public String deleteExam(@ModelAttribute Optional<Exam> optionalExam, Model model) {
        Exam exam = service.findExamById(optionalExam.get().getId());
        List<Exam> exams = service.findExamsOfCourse(optionalExam.get().getCourse());
        model.addAttribute("message", "آزمون حذف شد!!");
        model.addAttribute("exams", exams);
        return "exams-of-course";
    }

    @PostMapping(value = "/exam-operation", params = "action=edit")
    public String editExam(@ModelAttribute Optional<Exam> optionalExam, Model model){
        model.addAttribute("courses", model.addAttribute("courses", service.findMyCourses()));
        model.addAttribute("exam", service.findExamById(optionalExam.get().getId()));
        return "edit-exam";
    }

    @GetMapping(value = "/exam-operation")
    public String createNewExam(Model model){
        model.addAttribute("courses", service.findMyCourses());
        return "new-exam";
    }

    @PostMapping("/save-exam")
    public String createOrEditExam(@ModelAttribute Exam exam, Model model){
        service.saveExam(exam);
        model.addAttribute("exams", service.findExamsOfCourse(service.findCourseById(exam.getCourse().getId())));
        model.addAttribute("saveExamMassage", "آزمون ذخیره شد!");
        return "exams-of-course";
    }
}
