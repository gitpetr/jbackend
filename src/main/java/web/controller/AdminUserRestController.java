package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminUserRestController {

    private UserService userService;

    @Autowired
    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> listUsers(HttpServletRequest request, HttpServletResponse response) {
        return userService.getUsers();
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user/{name}")
    public User userEditForm(@PathVariable String name) {
        return userService.findByUsername(name);
    }
}
