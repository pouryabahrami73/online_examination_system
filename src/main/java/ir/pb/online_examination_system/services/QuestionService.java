package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Question;

public interface QuestionService {
    Question saveQuestion(Question question);

    Question findById(Long questionId);

    Question findQuestionById(long questionId);
}
