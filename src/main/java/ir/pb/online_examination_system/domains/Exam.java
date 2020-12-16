package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    public Exam() {
    }

    public Exam(Course course, Date date, Date time, int durationInMin) {
        this.course = course;
        this.date = date;
        this.time = time;
        this.durationInMin = durationInMin;
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
}
