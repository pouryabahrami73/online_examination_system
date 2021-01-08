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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static ir.pb.online_examination_system.domains.QuestionType.MULTIPLE_CHOICES;

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
    @Autowired
    private ExamSheetService examSheetService;

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
    public List<Course> findMyCourses() {
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
    public List<Question> findAllQuestionsOfCourse(String courseName) {
        List<Question> questions = new ArrayList<>();
        examQuestionService.findAllExamQuestionsOfCourse(courseName)
                .stream()
                .forEach(examQuestion -> {
                    if (!questions.contains(examQuestion.getQuestion())) {
                        questions.add(examQuestion.getQuestion());
                    }
                });
        return questions;
    }

    @Override
    public ExamQuestion makeExamQuestion(String courseName, Exam exam, Question question, Float mark) {
        return examQuestionService.makeExamQuestion(courseName, exam, question, mark);
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

    @Override
    public Question findQuestionById(Long questionId) {
        return questionService.findById(questionId);
    }

    @Override
    public void deleteExamQuestionByQuestionAndExam(Question question, Exam exam) {
        examQuestionService.deleteByQuestionAndExam(question, exam);
    }

    @Override
    public ExamQuestion findExamQuestionByQuestionAndExam(Question question, Exam exam) {
        return examQuestionService.findExamQuestionByQuestionAndExam(question, exam);
    }

    @Override
    public void deleteExamQuestion(ExamQuestion examQuestion) {
        examQuestionService.delete(examQuestion);
    }

    @Override
    public List<ExamSheet> findAllCompletedExamSheets(Exam exam) {
        List<ExamSheet> completedExamSheets = examSheetService.findAllCompleteExamSheets(exam);
        return completedExamSheets;
    }

    @Override
    public List<ExamSheet> findAllUncompletedExamSheets(Exam exam) {
        List<ExamSheet> uncompletedExamSheets = examSheetService.findAllUncompletedExamSheets(exam);
        return uncompletedExamSheets;
    }

    @Override
    public ExamSheet findExamSheetById(Long id) {
        return examSheetService.findById(id);
    }

    @Override
    public void correctMultipleChoiceQuestions(ExamSheet examSheet) {
        AtomicInteger i = new AtomicInteger();
        List<Float> eachQuestionMark = examSheet.getEachQuestionMark();
        List<Float> eachQuestionMarkGotten = examSheet.getStudentMarkFromEachQuestion();
        examSheet.getQuestions().stream().forEach(question -> {
            if (question.getType().equals(MULTIPLE_CHOICES)
                    & examSheet.getStudentAnswer().get(i.get()).equals(question.getAnswerKey())) {
                    eachQuestionMarkGotten.set(i.get(), eachQuestionMark.get(i.get()));
            }
        i.getAndIncrement();
        });
        examSheet.setStudentMarkFromEachQuestion(eachQuestionMarkGotten);
        examSheetService.save(examSheet);
    }

    @Override
    public void correctDescriptiveAndSubmitTotalGrade(Long id, Map<Integer, Float> marksMap) {
        ExamSheet examSheet = examSheetService.findById(id);
        List<Float> marksGotten = examSheet.getStudentMarkFromEachQuestion();
        for (int index : marksMap.keySet()){
            marksGotten.set(index, marksMap.get(index));
        }
        examSheet.setStudentMarkFromEachQuestion(marksGotten);
        examSheetService.save(examSheet);
    }
}
