package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface StudentService {
    List<Student> students();
    Student save(Student student);
    boolean isActiveStudent(User user);

    void setUser(User user);

    List<Student> findAll();

    List<Course> findMyCourses();

    Course findCourseById(Long id);

    List<Exam> findExamsOfCourse(Course course);

    List<Exam> findExams();

    Exam findExamById(Long id);

    ExamSheet makeNewExamSheet(Exam exam, List<Question> questions);

    void makeNewStudentExam(Exam exam, ExamSheet examSheet);

    ExamSheet findExamSheetById(long examSheetId);

    Question findQuestionById(long questionId);

    void setQuestionAnswer(ExamSheet examSheet, Question question, String answer);

    Integer continueExamSheetTime(ExamSheet examSheet);

    ExamSheet findUncompletedExamSheet();
}
