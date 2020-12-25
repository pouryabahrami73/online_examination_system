package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamQuestion;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.repositories.ExamQuestionRepository;
import ir.pb.online_examination_system.services.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Autowired
    private ExamQuestionRepository repository;
    @Autowired
    private ExamQuestion examQuestion;
    @Override
    public List<ExamQuestion> findAllExamQuestionsOfCourse(Course course) {
        return repository.findAllByCourse(course);
    }

    @Override
    public ExamQuestion makeExamQuestion(Course course, Exam exam, Question question) {
        examQuestion.setCourse(course);
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        return examQuestion;
    }

    @Override
    public ExamQuestion save(ExamQuestion examQuestion) {
        return repository.save(examQuestion);
    }
}
