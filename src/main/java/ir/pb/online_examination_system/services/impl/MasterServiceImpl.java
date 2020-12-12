package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Master;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.repositories.MasterRepository;
import ir.pb.online_examination_system.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepository repository;
    @Autowired
    private Master master;

    @Override
    public List<Master> masters() {
        return repository.findAll();
    }

    @Override
    public Master save(Master master) {
        return repository.save(master);
    }

    @Override
    public void setUser(User user) {
        master.setUser(user);
        repository.save(master);
    }

    @Override
    public List<Master> findAll() {
        return repository.findAll();
    }
}
