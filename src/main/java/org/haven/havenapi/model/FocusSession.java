package org.haven.havenapi.model;

import java.time.Duration;
import java.time.LocalDateTime;

public record FocusSession(String id, Duration duration, LocalDateTime startedAt, boolean completed) {
}
