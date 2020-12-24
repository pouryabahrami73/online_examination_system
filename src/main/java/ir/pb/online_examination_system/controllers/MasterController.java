package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.Question;
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

    @GetMapping("/course/{id}")
    public String showExamsOfCourse(@PathVariable Long id, Model model) {
        Course course = service.findCourseById(id);
        model.addAttribute("course", course);
        List<Exam> exams = service.findExamsOfCourse(course);
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

    @GetMapping("/edit-exam/{id}")
    public String editExam(@PathVariable Long id, Model model) {
        model.addAttribute("exam", service.findExamById(id));
        return "edit-exam";
    }

    @PostMapping("/new-exam")
    public String createNewExam(@ModelAttribute Optional<Course> courseOptional, Model model) {
        model.addAttribute("course", service.findCourseById(courseOptional.get().getId()));
        return "new-exam";
    }


    @PostMapping("/save-exam")
    public String createOrEditExam(@ModelAttribute Exam exam, Model model) {
        model.addAttribute("course", exam.getCourse());
        service.saveExam(exam);
        model.addAttribute("exams", service.findExamsOfCourse(exam.getCourse()));
        model.addAttribute("massage", "آزمون ذخیره شد!");
        return "exams-of-course";
    }

    @GetMapping("/add-question-to-exam/{id}")
    public String questionAdder(@PathVariable Long id, Model model) {
        Exam exam = service.findExamById(id);
        model.addAttribute("course", exam.getCourse());
        model.addAttribute("questions", service.findAllQuestionsOfCourse(exam.getCourse()));
        return "add-questions";
    }

    @GetMapping("/create-question-course/{id}")
    public String createQuestionForCourse(@PathVariable Long id, Model model) {
        Course course = service.findCourseById(id);
        model.addAttribute("course", course);
        return "create-question";
    }

    @PostMapping("/save-question")
    public String saveCreatedQuestion(@ModelAttribute Optional<Question> optionalQuestion, Model model){
        Question question = optionalQuestion.get();
        return "add-questions";
    }
}
