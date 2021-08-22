package lk.hotelManagement.backend.controller;

import lk.hotelManagement.backend.model.User;
import lk.hotelManagement.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> users() {
        return userService.users();
    }

    @PostMapping("/")
    public boolean createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public User userById(@RequestParam String userId) {
        return userService.userById(userId);
    }

    @PutMapping("/{userId}")
    public boolean editUser(@PathVariable String userId, @RequestBody User user) {
        return userService.editUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }
}
