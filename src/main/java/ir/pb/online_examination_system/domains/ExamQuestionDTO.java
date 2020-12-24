package ir.pb.online_examination_system.domains;

import java.util.List;

public class ExamQuestionDTO extends Question {
    private long examId;

    @Override
    public String getProblem() {
        return super.getProblem();
    }

    @Override
    public void setProblem(String problem) {
        super.setProblem(problem);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public List<String> getAlternatives() {
        return super.getAlternatives();
    }

    @Override
    public void setAlternatives(List<String> alternatives) {
        super.setAlternatives(alternatives);
    }

    @Override
    public QuestionType getType() {
        return super.getType();
    }

    @Override
    public void setType(QuestionType type) {
        super.setType(type);
    }

    @Override
    public String getAnswerKey() {
        return super.getAnswerKey();
    }

    @Override
    public void setAnswerKey(String answerKey) {
        super.setAnswerKey(answerKey);
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }
}
