package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.suormary.model.User;
import it.beije.suormary.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User getUsersById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User insertUsers(User user) {

        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);

    }

    public User updateUser(User user) {
        Optional<User> u = userRepository.findById(user.getId());
        if (!u.isPresent()) throw new RuntimeException("ID ERRATO!!!");
        User u2 = u.get();
        System.out.println("updated book : " + u2);

        BeanUtils.copyProperties(user, u2);

        userRepository.save(u2);

        System.out.println("updated book : " + u2);

        return user;
    }

}