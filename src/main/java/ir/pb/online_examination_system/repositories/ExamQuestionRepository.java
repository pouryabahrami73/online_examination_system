package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {
    Set<ExamQuestion> findAllByCourse(Course course);
    Set<ExamQuestion> findAllByExam(Exam exam);
}
