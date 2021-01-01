package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public String studentStarter(Model model){
        model.addAttribute("courses", service.findMyCourses());
        return "student";
    }

    @GetMapping("/course/{id}")
    public String showExamsOfCourse(@PathVariable Long id, Model model) {
        Course course = service.findCourseById(id);
        model.addAttribute("course", course);
        List<Exam> exams = service.findExamsOfCourse(course);
        List<Exam> doneExams = service.findExams();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        exams.stream().filter(exam -> !doneExams.contains(exam) & exam.getDate().after(date))
                .collect(Collectors.toList());
        model.addAttribute("exams", exams);
        return "student-exams-of-course";
    }

    @GetMapping("/take-exam/{id}")
    public String startExam(@PathVariable Long id, Model model){
        Exam exam = service.findExamById(id);
        List<Question> questions = new ArrayList<>();
        exam.getExamQuestions().stream().forEach(examQuestion -> questions.add(examQuestion.getQuestion()));
        model.addAttribute("exam", exam);
        model.addAttribute("questions", questions);
        return "exam";
    }
}
