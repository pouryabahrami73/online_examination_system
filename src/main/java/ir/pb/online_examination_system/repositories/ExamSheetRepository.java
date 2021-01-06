package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExamSheetRepository extends JpaRepository<ExamSheet, Long> {
    ExamSheet findByStudentAndComplete(Student student,boolean situation);
    Set<ExamSheet> findAllByExamAndComplete(Exam exam, Boolean situation);
}
