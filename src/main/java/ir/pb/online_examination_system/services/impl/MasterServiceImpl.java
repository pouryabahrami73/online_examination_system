package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.*;
import ir.pb.online_examination_system.repositories.MasterRepository;
import ir.pb.online_examination_system.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;
    @Autowired
    private Master master;
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private QuestionService questionService;
    @Override
    public List<Master> masters() {
        return repository.findAll();
    }

    @Override
    public Master save(Master master) {
        return repository.save(master);
    }

    @Override
    public void setUser(User user) {
        master.setUser(user);
        repository.save(master);
    }

    @Override
    public List<Master> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Course> findMyCourses(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User masterUser = userService.findByUserName(auth.getName());
        master = repository.findByUser(masterUser);
        return master.getCourses();
    }

    @Override
    public Course findCourseById(Long id) {
        return courseService.findById(id);
    }

    @Override
    public List<Exam> findExamsOfCourse(Course course) {
        return examService.findByCourse(course);
    }

    @Override
    public Exam findExamById(Long id) {
        return examService.findById(id).get();
    }

    @Override
    public Exam saveExam(Exam exam) {
        return examService.save(exam);
    }

    @Override
    public void deleteExam(Exam exam) {
        examService.deleteExam(exam);
    }

    @Override
    public List<Question> findAllQuestionsOfCourse(Course course) {
        List<Question> questions = new ArrayList<>();
        examQuestionService.findAllExamQuestionsOfCourse(course)
                .stream()
                .forEach(examQuestion -> {
                    if(!questions.contains(examQuestion.getQuestion())){
                        questions.add(examQuestion.getQuestion());
                    }
                });
        return questions;
    }

    @Override
    public ExamQuestion makeExamQuestion(Course course, Exam exam, Question question, Float mark) {
        return examQuestionService.makeExamQuestion(course, exam, question, mark);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionService.saveQuestion(question);
    }

    @Override
    public ExamQuestion saveExamQuestion(ExamQuestion examQuestion) {
        return examQuestionService.save(examQuestion);
    }

    @Override
    public List<Question> findAllQuestionsOfExam(Exam exam) {
        List<Question> questions = new ArrayList<>();
        examQuestionService.findAllQuestionsOfExam(exam)
                .stream()
                .forEach(examQuestion -> questions.add(examQuestion.getQuestion()));
        return questions;
    }

    @Override
    public float sumOfMarksUpToNow(Exam exam) {
        AtomicReference<Float> upToNowMarks = new AtomicReference<>((float) 0);
        examQuestionService.findAllQuestionsOfExam(exam)
                .stream()
                .forEach(examQuestion -> upToNowMarks.updateAndGet(v -> new Float((float) (v + examQuestion.getMark()))));
        return upToNowMarks.get();
    }
}
