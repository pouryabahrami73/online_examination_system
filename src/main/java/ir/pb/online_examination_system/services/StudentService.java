package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.User;

import java.util.List;

public interface StudentService {
    List<Student> students();
    Student save(Student student);
    boolean isActiveStudent(User user);

    void setUser(User user);

    List<Student> findAll();

}
