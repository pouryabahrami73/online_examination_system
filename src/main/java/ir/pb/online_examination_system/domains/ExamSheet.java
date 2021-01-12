package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ExamSheet extends BaseEntity<Long> {
    // complete field, represents whether the exam of a specific student was completed or not.
    @Column
    boolean complete;
    // questionInTimeOfExam field, keeps the questions' problem, which were asked, when the exam was started.
    @ElementCollection
    private List<String> questionsInTimeOfExam;
    // studentAnswers field, keeps the answers which the student answered finally, and in the order of questions field.
    @ElementCollection
    private List<String> studentAnswer;
    // questions field, keeps the questions in an exam and they may differ if the master change one of the questions.
    @ManyToMany
    @JoinTable(name = "examSheet_question",
            joinColumns = {@JoinColumn(name = "fk_examSheet")},
            inverseJoinColumns ={@JoinColumn(name = "fk_question")})
    private List<Question> questions;
    // examStartingTime field, represents the starting time of each specific examSheet and it's not updatable
    // to prevents the student to enroll in the same exam many times.
    @Column(updatable = false)
    /*@Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")*/
    private Date examStartingTime;
    // examFinishTime field, represents the starting time of each specific examSheet and it's not updatable
    // to prevents the student to enroll in the same exam many times.
    @Column(updatable = false)
    /*@Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")*/
    private Date examFinishTime;
    // each student field, represents that each student can take many exams.
    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;
    // exam field, represents that each exam can have many examSheets.
    @ManyToOne
    @JoinColumn
    private Exam exam;
    // eachQuestionMark field, keeps the mark of each question in the specific exam, in order of questions.
    @ElementCollection
    private List<Float> eachQuestionMark;
    // studentMarkFromEachQuestion field, keeps the mark gotten by student from each question.
    @ElementCollection
    private List<Float> studentMarkFromEachQuestion;
    // corrected field, represents whether the exam was corrected or not.
    @Column
    private boolean corrected;
    public ExamSheet() {
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
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

    public List<Float> getEachQuestionMark() {
        return eachQuestionMark;
    }

    public void setEachQuestionMark(List<Float> eachQuestionMark) {
        this.eachQuestionMark = eachQuestionMark;
    }

    public List<Float> getStudentMarkFromEachQuestion() {
        return studentMarkFromEachQuestion;
    }

    public void setStudentMarkFromEachQuestion(List<Float> studentMarkFromEachQuestion) {
        this.studentMarkFromEachQuestion = studentMarkFromEachQuestion;
    }

    public boolean isCorrected() {
        return corrected;
    }

    public void setCorrected(boolean corrected) {
        this.corrected = corrected;
    }
}
