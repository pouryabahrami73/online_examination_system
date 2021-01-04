package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.*;
import ir.pb.online_examination_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
        /*exams = exams.stream().filter(exam -> !doneExams.contains(exam) & exam.getDate().after(date))
                .collect(Collectors.toList());*/
        model.addAttribute("exams", exams);
        return "student-exams-of-course";
    }

    @GetMapping("/take-exam/{id}")
    public String startExam(@PathVariable Long id, Model model){
        Exam exam = service.findExamById(id);
        List<Question> questions = new ArrayList<>();
        exam.getExamQuestions().stream().forEach(examQuestion -> questions.add(examQuestion.getQuestion()));
        ExamSheet examSheet = service.makeNewExamSheet(exam, questions);
        service.makeNewStudentExam(exam, examSheet);
        model.addAttribute("examId", exam.getId());
        model.addAttribute("examTime", exam.getDurationInMin());
        model.addAttribute("questions", examSheet.getQuestions());
        model.addAttribute("examSheetId", examSheet.getId());
        return "exam";
    }

    @GetMapping("/continue-exam/{id}")
    public String continueExam(@PathVariable Long id, Model model){
        ExamSheet examSheet = service.findExamSheetById(id);
        model.addAttribute("examId", examSheet.getExam().getId());
        model.addAttribute("examTime", service.continueExamSheetTime(examSheet));
        model.addAttribute("questions", examSheet.getQuestions());
        model.addAttribute("examSheetId", examSheet.getId());
        return "exam";
    }

    @PostMapping("/answer-questions")
    public ResponseEntity<Object> examSheetAnswerSetter(@RequestBody QuestionAnswerDTO dto) {
        long questionId = dto.getQuestionId();
        String answer = dto.getAnswer();
        long examSheetId = dto.getExamSheetId();
        Question question = service.findQuestionById(questionId);
        ExamSheet examSheet = service.findExamSheetById(examSheetId);
        service.setQuestionAnswer(examSheet, question, answer);
        return null;
    }
}
