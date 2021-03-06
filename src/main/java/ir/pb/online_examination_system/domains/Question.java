package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity

public class Question extends BaseEntity<Long> {
    @Column(columnDefinition = "text")
    private String problem;
    @Column
    private String title;
    @Column
    private QuestionType type;
    @ElementCollection
    private List<String> alternatives;
    @Column
    private String answerKey;

    public Question() {
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }
}
