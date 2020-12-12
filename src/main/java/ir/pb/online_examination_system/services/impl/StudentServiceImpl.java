package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.repositories.StudentRepository;
import ir.pb.online_examination_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private Student student;

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
}
