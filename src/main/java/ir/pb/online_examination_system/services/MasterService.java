package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.*;

import java.util.List;
import java.util.Map;

public interface MasterService {
    List<Master> masters();
    Master save(Master master);

    void setUser(User user);

    List<Master> findAll();

    List<Course> findMyCourses();

    Course findCourseById(Long id);

    List<Exam> findExamsOfCourse(Course course);

    Exam findExamById(Long id);

    Exam saveExam(Exam exam);

    void deleteExam(Exam exam);

    List<Question> findAllQuestionsOfCourse(String courseName);

    ExamQuestion makeExamQuestion(String courseName, Exam exam, Question question, Float mark);

    Question saveQuestion(Question question);

    ExamQuestion saveExamQuestion(ExamQuestion examQuestion);

    List<Question> findAllQuestionsOfExam(Exam exam);

    float sumOfMarksUpToNow(Exam exam);

    Question findQuestionById(Long questionId);

    void deleteExamQuestionByQuestionAndExam(Question question, Exam exam);

    ExamQuestion findExamQuestionByQuestionAndExam(Question question, Exam exam);

    void deleteExamQuestion(ExamQuestion examQuestion);

    List<ExamSheet> findAllCompletedExamSheets(Exam exam);

    List<ExamSheet> findAllUncompletedExamSheets(Exam exam);

    ExamSheet findExamSheetById(Long id);

    void correctMultipleChoiceQuestions(ExamSheet examSheet);

    void correctDescriptiveAndSubmitTotalGrade(Long id, Map<Integer, Float> marksMap);

    void giveMakeToExamSheet(Long id);
}
