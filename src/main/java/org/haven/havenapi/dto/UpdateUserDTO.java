package org.haven.havenapi.dto;

import org.haven.havenapi.model.User;

public record UpdateUserDTO(String userName, int age, User.Gender gender) {
}
