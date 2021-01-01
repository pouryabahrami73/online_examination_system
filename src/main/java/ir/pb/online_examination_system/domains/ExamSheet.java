/*
package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
@Entity
public class ExamSheet extends BaseEntity<Long> {
    @ElementCollection
    @CollectionTable(name = "question_answer_mapping",
            joinColumns = {@JoinColumn(name = "fk_question", referencedColumnName = "id")})
    @MapKeyColumn(name = "question_problem")
    @Column(name = "question_answer")
    private Map<String, String> questionAnswer;
    @OneToOne(mappedBy = "examSheet")
    private StudentExam studentExams;
    @Column
    private Map<Question, String> questionStudentAnswer;
}
*/
