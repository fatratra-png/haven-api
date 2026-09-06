package org.haven.havenapi.service;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateJournalEntryDTO;
import org.haven.havenapi.model.JournalEntry;
import org.haven.havenapi.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class JournalService {
    private final JournalRepository journalRepository;

    public JournalEntry addNote(CreateJournalEntryDTO createJournalEntryDTO) throws SQLException {
        return journalRepository.insert(createJournalEntryDTO);
    }

    public List<JournalEntry> getDailyLog(String userId, LocalDate date) throws SQLException {
        return journalRepository.findByUserAndDate(userId, date);
    }
}
