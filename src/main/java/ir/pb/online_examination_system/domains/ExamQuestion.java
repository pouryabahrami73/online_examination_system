package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;

import javax.persistence.*;

@Entity
public class ExamQuestion extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "fk_exam")
    private Exam exam;
    @ManyToOne
    @JoinColumn(name = "fk_question")
    private Question question;
    @Column
    private float mark;

    public ExamQuestion() {
    }

    public ExamQuestion(Exam exam, Question question, float mark) {
        this.exam = exam;
        this.question = question;
        this.mark = mark;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
