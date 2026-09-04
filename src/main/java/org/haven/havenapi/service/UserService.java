package org.haven.havenapi.service;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateUserDTO;
import org.haven.havenapi.dto.UpdateUserDTO;
import org.haven.havenapi.exception.UserNotFoundException;
import org.haven.havenapi.model.User;
import org.haven.havenapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserDTO createUserDTO) {
        return userRepository.create(createUserDTO);
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(String id, UpdateUserDTO updateUserDTO) {
        return userRepository.update(id, updateUserDTO).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(String id) {
        if (!userRepository.deleteById(id)) {
            throw new UserNotFoundException(id);
        }
    }
}
