package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    List<Exam> findByCourse(Course course);

    Optional<Exam> findById(Long id);

    Exam save(Exam exam);

    void deleteExam(Exam exam);
}
