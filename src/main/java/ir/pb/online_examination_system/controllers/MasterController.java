package ir.pb.online_examination_system.controllers;

import ir.pb.online_examination_system.domains.*;
import ir.pb.online_examination_system.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ir.pb.online_examination_system.domains.QuestionType.DESCRIPTIVE;
import static ir.pb.online_examination_system.domains.QuestionType.MULTIPLE_CHOICES;

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

    @GetMapping("/delete-operation/{id}")
    public String deleteExam(@PathVariable Long id, Model model) {
        Exam exam = service.findExamById(id);
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

    @GetMapping("/new-exam/{id}")
    public String createNewExam(@PathVariable Long id, Model model) {
        model.addAttribute("course", service.findCourseById(id));
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
    public String showQuestions(@PathVariable Long id, Model model) {
        Exam exam = service.findExamById(id);
        model.addAttribute("exam", exam);
        questions(model, exam.getCourse().getName(), exam);
//        model.addAttribute("questions", service.findAllQuestionsOfCourse(exam.getCourse()));
//        model.addAttribute("questionsOfExam", service.findAllQuestionsOfExam(exam));
        return "add-questions";
    }

    public void questions(Model model, String courseName, Exam exam) {
        List<Question> questions = service.findAllQuestionsOfCourse(courseName);
        List<Question> questionsOfExam = service.findAllQuestionsOfExam(exam);
        model.addAttribute("questions", questions
                .stream()
                .filter(question -> !questionsOfExam.contains(question)).collect(Collectors.toList()));
        model.addAttribute("questionsOfExam", questionsOfExam);
        model.addAttribute("exam", exam);
        model.addAttribute("courseName", courseName);
    }

    @PostMapping("/add-stored-question-to-exma")
    public String addQuestionToExam(@ModelAttribute ExamQuestion examQuestion, Model model) {
        if (service.sumOfMarksUpToNow(examQuestion.getExam()) + examQuestion.getMark()
                <= examQuestion.getExam().getGrade()) {
            service.saveExamQuestion(examQuestion);
        } else {
            model.addAttribute("gradeError", "مجموع نمرات سوالات بیش از مجموع نمره آزمون است!");
        }
        questions(model, examQuestion.getCourseName(), examQuestion.getExam());
        return "add-questions";
    }

    @GetMapping("/create-question-exam/{id}")
    public String createQuestionForCourse(@PathVariable Long id, Model model) {
        Exam exam = service.findExamById(id);
        model.addAttribute("exam", exam);
        return "create-question";
    }

    @PostMapping("/save-question")
    public String saveCreatedQuestion(@ModelAttribute Exam exam, Question question, Float mark, Model model) {
        question.setId(null);
        service.saveQuestion(question);
        Exam exam1 = service.findExamById(exam.getId());
        ExamQuestion examQuestion = service.makeExamQuestion(exam1.getCourse().getName()
                , exam1
                , question, mark);
        service.saveExamQuestion(examQuestion);

        questions(model, exam1.getCourse().getName(), exam1);
//        model.addAttribute("exam", exam);
//        model.addAttribute("questions", service.findAllQuestionsOfCourse(exam.getCourse()));
        return "add-questions";
    }

    @GetMapping("/delete-question-from-exam/{questionId}/{examId}")
    public String deleteQuestionFromExam(@PathVariable Long questionId, @PathVariable Long examId, Model model) {
        Exam exam = service.findExamById(examId);
        Question question = service.findQuestionById(questionId);
//        service.deleteExamQuestionByQuestionAndExam(question, exam);
        ExamQuestion examQuestion = service.findExamQuestionByQuestionAndExam(question, exam);
        service.deleteExamQuestion(examQuestion);
        questions(model, exam.getCourse().getName(), exam);
        return "add-questions";
    }

    @GetMapping("/students-took-exam/{examId}")
    public String showStudentsWhoCompletedExam(@PathVariable Long examId, Model model) {
        Exam exam = service.findExamById(examId);
        List<ExamSheet> completedExamSheets = service.findAllCompletedExamSheets(exam);
        List<ExamSheet> uncompletedExamSheets = service.findAllUncompletedExamSheets(exam);
        /*model.addAttribute("complete", completedExamSheets);
        model.addAttribute("incomplete", uncompletedExamSheets);*/
        List<ExamSheet> allSheets = completedExamSheets;
        uncompletedExamSheets.stream()
                .forEach(examSheet -> allSheets.add(examSheet));
        model.addAttribute("examSheets", allSheets);
        model.addAttribute("students", completedExamSheets.size() + uncompletedExamSheets.size());
        model.addAttribute("exam", exam);
        return "exam-takers";
    }

    @GetMapping("/see-examSheet/{id}")
    public String seeStudentExamSheet(@PathVariable Long id, Model model) {
        ExamSheet examSheet = service.findExamSheetById(id);
        List<Question> questions = examSheet.getQuestions();
        List<String> questionsInTimeOfExam = examSheet.getQuestionsInTimeOfExam();
        List<String> studentAnswers = examSheet.getStudentAnswer();
        List<String> studentAnswersToDescriptive = new ArrayList<>();
        questions.stream()
                .forEach(question -> {
                    if (question.getType().equals(DESCRIPTIVE)) {
                        studentAnswersToDescriptive.add(studentAnswers.get(questions.indexOf(question)));
                    }
                });
        List<String> descriptiveQuestions = questionsInTimeOfExam.stream()
                .filter(question -> questions.get(questionsInTimeOfExam.indexOf(question)).getType().equals(DESCRIPTIVE))
                .collect(Collectors.toList());
        List<Float> eachQuestionMark = new ArrayList<>();
        List<Integer> descriptiveIndex = new ArrayList<>();
        questions.stream().forEach(question -> {
            if(question.getType().equals(DESCRIPTIVE)){
                eachQuestionMark.add(examSheet.getEachQuestionMark().get(questions.indexOf(question)));
                descriptiveIndex.add(questions.indexOf(question));
            }
        });
        service.correctMultipleChoiceQuestions(examSheet);
        model.addAttribute("descriptiveQuestions", descriptiveQuestions);
        model.addAttribute("descriptiveIndex", descriptiveIndex);
        model.addAttribute("eachQuestionMark", eachQuestionMark);
        model.addAttribute("examSheetId", examSheet.getId());
        model.addAttribute("descriptiveAnswers", studentAnswersToDescriptive);
        return "student-exam-sheet";
    }

    @PostMapping("/set-marks-of-descriptive-questions/{id}")
    public ResponseEntity<Object> examSheetAnswerSetter(@PathVariable Long id,
                                                        @RequestBody Map<Integer, Float> marksMap) {
        service.correctDescriptiveAndSubmitTotalGrade(id, marksMap);
        service.giveMakeToExamSheet(id);
        return null;
    }
}
