package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.ExamSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamSheetRepository extends JpaRepository<ExamSheet, Long> {
}
