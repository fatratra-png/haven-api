package org.haven.havenapi.controller;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateJournalEntryDTO;
import org.haven.havenapi.model.JournalEntry;
import org.haven.havenapi.service.JournalService;
import org.haven.havenapi.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/journals")

public class JournalController {
    private final JournalService journalService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<JournalEntry> create(@RequestBody CreateJournalEntryDTO createJournalEntryDTO) throws SQLException {
        return ResponseEntity.status(HttpStatus.CREATED).body(journalService.addNote(createJournalEntryDTO));
    }

    @GetMapping
    public List<JournalEntry> getByDate(@RequestParam String userId,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) throws SQLException {
        return journalService.getDailyLog(userId, date);
    }
}
