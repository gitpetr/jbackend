package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminUserRestController {

    private UserService userService;

    @Autowired
    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @PostMapping("/saveUser")
    public User saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user/{name}")
    public User userEditForm(@PathVariable String name) {
        User user = userService.findByUsername(name);
        return user;
    }
}
