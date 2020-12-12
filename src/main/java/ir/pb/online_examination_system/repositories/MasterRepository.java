package ir.pb.online_examination_system.repositories;

import ir.pb.online_examination_system.domains.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
}
