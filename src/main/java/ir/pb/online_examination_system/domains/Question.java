package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question extends BaseEntity<Long> {

    @Column(columnDefinition = "text")
    private String problem;

    @OneToMany(mappedBy = "question")
    private List<ExamQuestion> examQuestions;

    @Column
    private String title;

    @Column
    private float mark;

    @ElementCollection
    private List<String> alternatives;

    @Column
    private String answer;

    public Question() {
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public List<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
