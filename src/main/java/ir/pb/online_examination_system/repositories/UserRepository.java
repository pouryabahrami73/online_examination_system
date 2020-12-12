package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    List<User> findAllByActive(boolean active);
    List<User> findAllByActiveAndFirstName(boolean active, String firstName);
    List<User> findAllByActiveAndLastName(boolean active, String lastName);
    List<User> findAllByActiveAndRoles(boolean active, String roles);
    List<User> findAllByActiveAndFirstNameAndLastName(boolean active, String firstName, String lastName);
    List<User> findAllByActiveAndFirstNameAndRoles(boolean active, String firstName, String roles);
    List<User> findAllByActiveAndLastNameAndRoles(boolean active, String lastName, String roles);
    List<User> findAllByActiveAndFirstNameAndLastNameAndRoles(boolean active, String firstName, String lastName, String roles);
}
