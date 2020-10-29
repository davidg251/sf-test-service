package com.acalderon.demoBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @RequestMapping("/api/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/api/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}