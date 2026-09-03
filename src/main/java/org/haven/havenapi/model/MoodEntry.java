package org.haven.havenapi.model;

import java.time.LocalDateTime;

public record MoodEntry(String id, String mood, String note, LocalDateTime timestamp) {
}
