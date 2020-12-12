package ir.pb.online_examination_system.domains;

import ir.pb.online_examination_system.base.domians.BaseEntity;

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
    private Date date;
    @Column
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column
    private int durationInMin;

}
