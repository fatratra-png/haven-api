package org.haven.havenapi.model;

import java.time.LocalDateTime;

public record JournalEntry(String id, String userId, String content, LocalDateTime timestamp) {
}
