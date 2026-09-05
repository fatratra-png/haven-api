package org.haven.havenapi.service;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateMoodEntryDTO;
import org.haven.havenapi.model.MoodEntry;
import org.haven.havenapi.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class MoodService {
    private final MoodRepository moodRepository;

    public MoodEntry recordMood(CreateMoodEntryDTO createMoodEntryDTO) throws SQLException {
        return moodRepository.insert(createMoodEntryDTO);
    }

    public List<MoodEntry> getDailyHistory(String userId, LocalDate date) throws SQLException {
        return moodRepository.findByUserAndDate(userId, date);
    }
}
