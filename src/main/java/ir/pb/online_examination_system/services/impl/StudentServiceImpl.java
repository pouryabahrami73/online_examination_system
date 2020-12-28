package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.repositories.StudentRepository;
import ir.pb.online_examination_system.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private Student student;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;
    @Autowired
    private StudentExamService studentExamService;

    @Override
    public List<Student> students() {
        return repository.findAll();
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public boolean isActiveStudent(User user) {
        return false;
    }

    @Override
    public void setUser(User user) {
        student.setUser(user);
        repository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Course> findMyCourses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User studentUser = userService.findByUserName(auth.getName());
        student = repository.findByUser(studentUser);
        return student.getCourses();
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
    public List<Exam> findExams() {
        List<Exam> exams = new ArrayList<>();
        studentExamService.findStudentExams(student).stream().forEach(studentExam -> exams.add(studentExam.getExam()));
        return exams;
    }
}
