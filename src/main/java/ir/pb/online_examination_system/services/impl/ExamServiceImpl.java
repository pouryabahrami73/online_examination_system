package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.repositories.ExamRepository;
import ir.pb.online_examination_system.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository repository;
    @Override
    public List<Exam> findByCourse(Course course) {
        return repository.findAllByCourse(course);
    }

    @Override
    public Optional<Exam> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Exam save(Exam exam) {
        return repository.save(exam);
    }

    @Override
    public void deleteExam(Exam exam) {
        repository.delete(exam);
    }
}
