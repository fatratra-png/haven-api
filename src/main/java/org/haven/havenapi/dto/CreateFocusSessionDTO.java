package org.haven.havenapi.dto;

import java.time.Duration;

public record CreateFocusSessionDTO(String userId, Duration duration) {
}
