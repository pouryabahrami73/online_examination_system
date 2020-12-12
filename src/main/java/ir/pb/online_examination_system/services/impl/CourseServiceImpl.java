package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.repositories.CourseRepository;
import ir.pb.online_examination_system.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;


    @Override
    public Course save(Course course) {
        return repository.save(course);
    }
}

