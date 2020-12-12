package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User findById(Long id);
    List<User> findAllActiveOrInactive(boolean situation);
    List<User> findAllActiveOrInactiveByFirstname(boolean situation, String firstName);
    List<User> findAllActiveOrInactiveByLastName(boolean situation, String lastName);
    List<User> findAllActiveOrInactiveByRoles(boolean situation, String roles);
    List<User> findAllActiveOrInactiveByFirstNameAndLastName(boolean situation, String firstName, String lastName);
    List<User> findAllActiveOrInactiveByFirstNameAndRoles(boolean situation, String firstName, String roles);
    List<User> findAllActiveOrInactiveByLastNameAndRoles(boolean situation, String lastName, String roles);
    List<User> findAllActiveOrInactiveByFirstNameAndLastNameAndRoles(boolean situation, String firstName, String lastName, String roles);
    String hashPass(String pass);
}
