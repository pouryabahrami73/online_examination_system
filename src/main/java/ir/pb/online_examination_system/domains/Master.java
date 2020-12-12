package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Component
public class Master extends BaseEntity<Long> {
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;
    @OneToMany(mappedBy = "master")
    private List<Course> courses;
    /*@OneToMany(mappedBy = "master")
    private Set<Question> questions;
    @OneToMany(mappedBy = "master")
    private Set<Exam> exams;*/

    public Master() {
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

    @Override
    public String toString() {
        return this.getId() + " " + this.user.getFirstName() + " " + this.user.getLastName();
    }
}
