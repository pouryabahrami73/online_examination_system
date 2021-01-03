package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class ExamSheet extends BaseEntity<Long> {
    @ElementCollection
    private List<String> questionsInTimeOfExam;
    @ElementCollection
    private List<String> studentAnswer;
    @ManyToMany
    @JoinTable(name = "examSheet_question",
            joinColumns = {@JoinColumn(name = "fk_examSheet")},
            inverseJoinColumns ={@JoinColumn(name = "fk_question")})
    private List<Question> questions;
    @Column
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date examStartingTime;
    @Column
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date examFinishTime;
    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;
    @ManyToOne
    @JoinColumn
    private Exam exam;
    public ExamSheet() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<String> getQuestionsInTimeOfExam() {
        return questionsInTimeOfExam;
    }

    public void setQuestionsInTimeOfExam(List<String> questionsInTimeOfExam) {
        this.questionsInTimeOfExam = questionsInTimeOfExam;
    }

    public Date getExamFinishTime() {
        return examFinishTime;
    }

    public void setExamFinishTime(Date examFinishTime) {
        this.examFinishTime = examFinishTime;
    }

    public List<String> getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(List<String> studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Date getExamStartingTime() {
        return examStartingTime;
    }

    public void setExamStartingTime(Date examStartingTime) {
        this.examStartingTime = examStartingTime;
    }
}
