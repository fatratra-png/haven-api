package org.haven.havenapi.controller;


import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CompleteFocusSessionDTO;
import org.haven.havenapi.dto.CreateFocusSessionDTO;
import org.haven.havenapi.model.FocusSession;
import org.haven.havenapi.service.FocusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/focus-sessions")
public class FocusController {
    private final FocusService focusService;

    @PostMapping
    public ResponseEntity<FocusSession> start(@RequestBody CreateFocusSessionDTO createFocusSessionDTO) throws SQLException {
        return ResponseEntity.status(HttpStatus.CREATED).body(focusService.startSession(createFocusSessionDTO));
    }

    @PatchMapping
    public ResponseEntity<Void> complete(@PathVariable String id,
                                         @RequestBody CompleteFocusSessionDTO completeFocusSessionDTO
                                         ) throws SQLException {
        focusService.completeSession(id,completeFocusSessionDTO.userId());
        return ResponseEntity.noContent().build();
    }
}
