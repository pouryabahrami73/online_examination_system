package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {
    List<StudentExam> findAllByStudent(Student student);
    StudentExam findByExamSheet(ExamSheet examSheet);
}
