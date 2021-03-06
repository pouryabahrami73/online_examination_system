package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Exam extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "fk_course")
    private Course course;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date time;
    @Column
    private int durationInMin;
    @Column(columnDefinition = "text")
    private String information;
    @Column
    private String title;
    @OneToMany(mappedBy = "exam", fetch = FetchType.EAGER)
    private List<ExamQuestion> examQuestions;
    @Column
    private int grade;

    public Exam() {
    }

    public Exam(Course course, Date date, Date time, int durationInMin, String information, String title) {
        this.course = course;
        this.date = date;
        this.time = time;
        this.durationInMin = durationInMin;
        this.information = information;
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }

    @Override
    public String toString() {
        return course + " | " + title + " | " + date + " " + time + " | " + durationInMin + "min";
    }
}
