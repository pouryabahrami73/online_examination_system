package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUser(User studentUser);
}
