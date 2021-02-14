package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Master;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.services.impl.Filter;

import java.util.List;

public interface AdminService {
    List<User> findAll();
//    List<User> findAll(List<Filter> filters);
//    List<User> filteredUsers(String filterFirstName, String filterLastName, String filterRoles);
    List<User> filteredUsers(List<Filter> filters);

    User findByUserId(Long id);

    void saveUser(User user);

    void saveMaster(User user);

    void saveStudent(User user);

    void saveCourse(Course course);

    List<Master> masters();

    List<Student> students();
}
