package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.repositories.QuestionRepository;
import ir.pb.online_examination_system.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository repository;
    @Override
    public Question saveQuestion(Question question) {
        return repository.save(question);
    }

    @Override
    public Question findById(Long questionId) {
        return repository.findById(questionId).get();
    }
}
