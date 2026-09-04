package org.haven.havenapi.controller;

import lombok.AllArgsConstructor;
import org.haven.havenapi.model.User;
import org.haven.havenapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
