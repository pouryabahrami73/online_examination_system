package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Student;

import java.util.List;

public interface CourseService {
    Course save(Course course);

    Course findById(Long id);
}
