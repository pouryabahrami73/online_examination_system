package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.*;

import java.util.List;

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

    List<Question> findAllQuestionsOfCourse(Course course);

    ExamQuestion makeExamQuestion(Course course, Exam exam, Question question, Float mark);

    Question saveQuestion(Question question);

    ExamQuestion saveExamQuestion(ExamQuestion examQuestion);

    List<Question> findAllQuestionsOfExam(Exam exam);

    float sumOfMarksUpToNow(Exam exam);
}
