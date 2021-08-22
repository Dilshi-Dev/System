package lk.hotelManagement.backend.service;
import lk.hotelManagement.backend.model.User;
import lk.hotelManagement.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> users() {
        return userRepository.users();
    }

    public boolean createUser(User user) {
        return userRepository.createUser(user);
    }

    public User userById(String userId) {
        return userRepository.userById(userId);
    }
    public boolean editUser( String userId, User user) {
        return userRepository.editUser(userId,user);
    }
    public boolean deleteUser(String userId) {
        return userRepository.deleteUser(userId);
    }

    public User login(String userName,String password) {
        return userRepository.login(userName,password);
    }
}
