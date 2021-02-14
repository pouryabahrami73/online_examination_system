package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.MyUserDetails;
import ir.pb.online_examination_system.domains.User;
import ir.pb.online_examination_system.repositories.UserRepository;
import ir.pb.online_examination_system.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService, UserService {
    @Autowired
    private CustomUserRepository customUserRepository;
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(userName);

        user.orElseThrow(()-> new UsernameNotFoundException("کاربری با این نام کاربری یافت نشد!"));
        return user.map(MyUserDetails::new).get();
    }
    @Override
    public User save(User user) {
        User savedUser = repository.save(user);
        return savedUser;
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<User> findAllActiveOrInactive(boolean situation) {
        return repository.findAllByActive(situation);
    }

    @Override
    public List<User> findAllActiveOrInactiveByFirstname(boolean situation, String firstName) {
        return repository.findAllByActiveAndFirstName(false, firstName);
    }

    /*@Override
    public List<User> findAllActiveOrInactiveByLastName(boolean situation, String lastName) {
        return repository.findAllByActiveAndLastName(false, lastName);
    }*/

    @Override
    public List<User> findAllActiveOrInactiveByRoles(boolean situation, String roles) {
        return repository.findAllByActiveAndRoles(false, roles);
    }

    @Override
    public List<User> findAllActiveOrInactiveByFirstNameAndLastName(boolean situation, String firstName, String lastName) {
        return repository.findAllByActiveAndFirstNameAndLastName(false, firstName, lastName);
    }

    @Override
    public List<User> findAllActiveOrInactiveByFirstNameAndRoles(boolean situation, String firstName, String roles) {
        return repository.findAllByActiveAndFirstNameAndRoles(false, firstName, roles);
    }

    @Override
    public List<User> findAllActiveOrInactiveByLastNameAndRoles(boolean situation, String lastName, String roles) {
        return repository.findAllByActiveAndLastNameAndRoles(false, lastName, roles);
    }

    @Override
    public List<User> findAllActiveOrInactiveByFirstNameAndLastNameAndRoles(boolean situation, String firstName, String lastName, String roles) {
        return repository.findAllByActiveAndFirstNameAndLastNameAndRoles(false, firstName, lastName, roles);
    }

    @Override
    public String hashPass(String pass) {
        String passWord = passwordEncoder.encode(pass);
        return passWord;
    }

    @Override
    public User findByUserName(String name) {
        try {
            return repository.findByUserName(name).get();
        }catch (NoSuchElementException e){
            LoggerFactory.getLogger(MyUserDetails.class).warn("no value");
        }
        return null;
    }

    @Override
    public User findByNationalCode(long nationalCode) {
        return repository.findByNationalCode(nationalCode);
    }

    @Override
    public List<User> findAll(List<Filter> filters) {

        return customUserRepository.getQueryResult(filters);
    }
}
