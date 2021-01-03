package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.StudentExam;
import ir.pb.online_examination_system.repositories.StudentExamRepository;
import ir.pb.online_examination_system.services.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {
    @Autowired
    private StudentExamRepository repository;
    @Override
    public List<StudentExam> findStudentExams(Student student) {
        return repository.findAllByStudent(student);
    }

    @Override
    public void makeNewStudentExam(Exam exam, Student student, ExamSheet examSheet) {
        StudentExam studentExam = new StudentExam();
        studentExam.setExam(exam);
        studentExam.setStudent(student);
        studentExam.setExamSheet(examSheet);
        repository.save(studentExam);
    }
}
