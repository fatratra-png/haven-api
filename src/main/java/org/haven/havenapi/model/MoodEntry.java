package org.haven.havenapi.model;

import java.time.LocalDateTime;

public record MoodEntry(String id, Mood mood, String note, LocalDateTime timestamp) {
}
