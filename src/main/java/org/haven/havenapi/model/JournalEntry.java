package org.haven.havenapi.model;

import java.time.LocalDateTime;

public record JournalEntry(String id, String content, LocalDateTime timestamp) {
}
