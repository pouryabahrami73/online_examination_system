package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findAllByCourse(Course course);
}
