package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.Master;
import ir.pb.online_examination_system.domains.User;

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
}
