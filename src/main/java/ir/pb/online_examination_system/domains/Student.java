package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Component
public class Student extends BaseEntity<Long> {
    @OneToOne
    @JoinColumn(name = "fk_user")
    private User user;
    @ManyToMany(mappedBy = "students",cascade = CascadeType.ALL)
    private List<Course> courses;
    @OneToMany(mappedBy = "student")
    private List<StudentExam> studentExams;

    public Student() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<StudentExam> getStudentExams() {
        return studentExams;
    }

    public void setStudentExams(List<StudentExam> studentExams) {
        this.studentExams = studentExams;
    }

    @Override
    public String toString() {
        String str = user.getFirstName() + " " + user.getLastName() + " " + this.getId();
        return str;
    }
}
