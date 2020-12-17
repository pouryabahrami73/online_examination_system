package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("course", course);
        List<Exam> exams = service.findExamsOfCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("exams", exams);
        return "exams-of-course";
    }

    @PostMapping("/delete-operation")
    public String deleteExam(@ModelAttribute Optional<Exam> optionalExam, Model model) {
        Exam exam = service.findExamById(optionalExam.get().getId());
        service.deleteExam(exam);
        List<Exam> exams = service.findExamsOfCourse(exam.getCourse());
        model.addAttribute("course", exam.getCourse());
        model.addAttribute("message", "آزمون حذف شد!!");
        model.addAttribute("exams", exams);
        return "exams-of-course";
    }

    @PostMapping("/edit-operation")
    public String editExam(@ModelAttribute Optional<Exam> optionalExam, Model model){
        model.addAttribute("exam", service.findExamById(optionalExam.get().getId()));
        return "edit-exam";
    }

    @PostMapping("/new-exam")
    public String createNewExam(@ModelAttribute Course course, Model model){
//        Course course = examOptional.get().getCourse();
        model.addAttribute("course", course);
        return "new-exam";
    }

    @PostMapping("/save-exam")
    public String createOrEditExam(@ModelAttribute Exam exam, Model model){
        service.saveExam(exam);
        model.addAttribute("course", exam.getCourse());
        model.addAttribute("exams", service.findExamsOfCourse(service.findCourseById(exam.getCourse().getId())));
        model.addAttribute("massage", "آزمون ذخیره شد!");
        return "exams-of-course";
    }
}
