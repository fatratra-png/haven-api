package org.haven.havenapi.dto;

import org.haven.havenapi.model.User;

public record CreateUserDTO(String userName, int age, User.Gender gender) {
}
