package org.haven.havenapi.controller;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateMoodEntryDTO;
import org.haven.havenapi.model.MoodEntry;
import org.haven.havenapi.service.MoodService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/moods")
public class MoodController {
    private final MoodService moodService;

    @PostMapping
    public ResponseEntity<MoodEntry> create(@RequestBody CreateMoodEntryDTO createMoodEntryDTO) throws SQLException {
        return ResponseEntity.status(HttpStatus.CREATED).body(moodService.recordMood(createMoodEntryDTO));
    }

    @GetMapping
    public List<MoodEntry> getByDate(
            @RequestParam String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) throws SQLException {
        return moodService.getDailyHistory(userId, date);
    }
}
