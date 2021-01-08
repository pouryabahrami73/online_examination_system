package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.StudentExam;

import java.util.List;

public interface StudentExamService {
    List<StudentExam> findStudentExams(Student student);

    void makeNewStudentExam(Exam exam, Student student, ExamSheet examSheet);

    StudentExam findStudentExamByExamSheet(ExamSheet examSheet);

    void save(StudentExam studentExam);
}
