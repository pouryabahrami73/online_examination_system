package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.ExamQuestion;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.repositories.ExamQuestionRepository;
import ir.pb.online_examination_system.services.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Autowired
    private ExamQuestionRepository repository;
    @Override
    public List<ExamQuestion> findAllExamQuestionsOfCourse(Course course) {
        return repository.findAllByCourse(course);
    }
}
