package org.haven.havenapi.dto;

import org.haven.havenapi.model.User;

public record UpdateUserRequest(String userName, int age, User.Gender gender) {
}
