package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Master;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.repositories.MasterRepository;
import ir.pb.online_examination_system.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private UserService userService;
    @Autowired
    private MasterService masterService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @Override
    public List<User> filteredUsers(String filterFirstName, String filterLastName, String filterRoles){
        List<User> users = new ArrayList<>();
        if (filterFirstName.equals("") & filterLastName.equals("") & filterRoles == null){
            users = userService.findAllActiveOrInactive(false);
        }else if (filterLastName.equals("") & filterRoles == null){
            users = userService.findAllActiveOrInactiveByFirstname(false, filterFirstName);
        }else if (filterFirstName.equals("") & filterRoles == null){
            users = userService.findAllActiveOrInactiveByLastName(false, filterLastName);
        }else if (filterFirstName.equals("") & filterLastName.equals("")){
            users = userService.findAllActiveOrInactiveByRoles(false, filterRoles);
        }else if(filterRoles == null){
            users = userService.findAllActiveOrInactiveByFirstNameAndLastName(false, filterFirstName, filterLastName);
        }else if (filterFirstName.equals("")){
            users = userService.findAllActiveOrInactiveByLastNameAndRoles(false, filterLastName, filterRoles);
        }else if (filterLastName.equals("")){
            users = userService.findAllActiveOrInactiveByFirstNameAndRoles(false, filterFirstName, filterRoles);
        }
        return users;
    }

    @Override
    public User findByUserId(Long id) {
        return userService.findById(id);
    }

    @Override
    public void saveUser(User user) {
        userService.save(user);
    }

    @Override
    public void saveMaster(User user) {
        masterService.setUser(user);
    }

    @Override
    public void saveStudent(User user) {
        studentService.setUser(user);
    }

    @Override
    public List<Master> masters() {
        return masterService.findAll();
    }

    @Override
    public List<Student> students() {
        return studentService.findAll();
    }

    @Override
    public void saveCourse(Course course) {
        courseService.save(course);
    }
}
