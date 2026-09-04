package org.haven.havenapi.controller;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateUserDTO;
import org.haven.havenapi.dto.UpdateUserDTO;
import org.haven.havenapi.model.User;
import org.haven.havenapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User get(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
        User created = userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(id, updateUserDTO);
    }


}
