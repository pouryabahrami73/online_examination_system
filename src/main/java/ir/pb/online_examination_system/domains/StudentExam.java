package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class StudentExam extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "fk_exam")
    private Exam exam;
    /*@Column
    private String courseName;*/
    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;
    @OneToOne
    @JoinColumn(name = "fk_examsheet")
    private ExamSheet examSheet;
    @Column
    private float grade;

    public StudentExam() {
    }

    public StudentExam(Exam exam, Student student, float grade) {
        this.exam = exam;
        this.student = student;
        this.grade = grade;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamSheet getExamSheet() {
        return examSheet;
    }

    public void setExamSheet(ExamSheet examSheet) {
        this.examSheet = examSheet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
