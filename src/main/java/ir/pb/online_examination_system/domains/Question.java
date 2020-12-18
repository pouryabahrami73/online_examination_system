package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question extends BaseEntity<Long> {
    @ManyToMany
    @JoinTable(name = "question_exam"
            ,joinColumns = {@JoinColumn(name = "fk_question")}
            ,inverseJoinColumns = {@JoinColumn(name = "fk_exam")})
    private List<Exam> exams;

    @Column
    private String title;

    @Column
    private float mark;
}
