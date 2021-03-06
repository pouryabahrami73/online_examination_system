package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamQuestion;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.repositories.ExamQuestionRepository;
import ir.pb.online_examination_system.services.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Autowired
    private ExamQuestionRepository repository;
    @Override
    public List<ExamQuestion> findAllExamQuestionsOfCourse(String courseName) {
        return repository.findAllByCourseName(courseName).stream().collect(Collectors.toList());
    }

    @Override
    public ExamQuestion makeExamQuestion(String courseName, Exam exam, Question question, Float mark) {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setCourseName(courseName);
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        examQuestion.setMark(mark);
        return examQuestion;
    }

    @Override
    public ExamQuestion save(ExamQuestion examQuestion) {
        return repository.save(examQuestion);
    }

    @Override
    public List<ExamQuestion> findAllQuestionsOfExam(Exam exam) {
        return repository.findAllByExam(exam).stream().collect(Collectors.toList());
    }

    @Override
    public void deleteByQuestionAndExam(Question question, Exam exam) {
        repository.removeByQuestionAndExam(question, exam);
    }

    @Override
    public ExamQuestion findExamQuestionByQuestionAndExam(Question question, Exam exam) {
        return repository.findByQuestionAndExam(question, exam);
    }

    @Override
    public void delete(ExamQuestion examQuestion) {
        examQuestion.setExam(null);
        repository.save(examQuestion);
    }

    @Override
    public Float findMarkOfQuestion(Exam exam, Question question) {
        float mark = repository.findByExamAndQuestion(exam, question).getMark();
        return mark;
    }
}
