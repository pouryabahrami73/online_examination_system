package ir.pb.online_examination_system.dtos;

public class QuestionAnswerDTO {
    private String answer;
    private long questionId;
    private long examSheetId;

    public QuestionAnswerDTO() {
    }

    public long getExamSheetId() {
        return examSheetId;
    }

    public void setExamSheetId(long examSheetId) {
        this.examSheetId = examSheetId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
