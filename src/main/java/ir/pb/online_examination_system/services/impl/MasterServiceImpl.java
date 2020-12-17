package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.Master;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.repositories.CourseRepository;
import ir.pb.online_examination_system.repositories.MasterRepository;
import ir.pb.online_examination_system.repositories.UserRepository;
import ir.pb.online_examination_system.services.CourseService;
import ir.pb.online_examination_system.services.ExamService;
import ir.pb.online_examination_system.services.MasterService;
import ir.pb.online_examination_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;
    @Autowired
    private Master master;
    @Override
    public List<Master> masters() {
        return repository.findAll();
    }

    @Override
    public Master save(Master master) {
        return repository.save(master);
    }

    @Override
    public void setUser(User user) {
        master.setUser(user);
        repository.save(master);
    }

    @Override
    public List<Master> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Course> findMyCourses(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User masterUser = userService.findByUserName(auth.getName());
        master = repository.findByUser(masterUser);
        return master.getCourses();
    }

    @Override
    public Course findCourseById(Long id) {
        return courseService.findById(id);
    }

    @Override
    public List<Exam> findExamsOfCourse(Course course) {
        return examService.findByCourse(course);
    }

    @Override
    public Exam findExamById(Long id) {
        return examService.findById(id).get();
    }

    @Override
    public Exam saveExam(Exam exam) {
        return examService.save(exam);
    }

    @Override
    public void deleteExam(Exam exam) {
        examService.deleteExam(exam);
    }
}
