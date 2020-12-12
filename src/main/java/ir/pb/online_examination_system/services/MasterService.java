package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Master;
import ir.pb.online_examination_system.domains.User;

import java.util.List;

public interface MasterService {
    List<Master> masters();
    Master save(Master master);

    void setUser(User user);

    List<Master> findAll();
}
