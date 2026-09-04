package org.haven.havenapi.service;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateUserDTO;
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
}
