package org.haven.havenapi.model;

import java.time.LocalDateTime;

public record MoodEntry(String id, String userId, Mood mood, String note, LocalDateTime timestamp) {
}
