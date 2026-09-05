package org.haven.havenapi.dto;

import org.haven.havenapi.model.Mood;

public record CreateMoodEntryDTO(String userId, Mood mood, String note) {
}
