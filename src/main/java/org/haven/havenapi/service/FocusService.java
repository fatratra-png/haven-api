package org.haven.havenapi.service;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.dto.CreateFocusSessionDTO;
import org.haven.havenapi.exception.FocusSessionNotFoundException;
import org.haven.havenapi.model.FocusSession;
import org.haven.havenapi.repository.FocusRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor

public class FocusService {
    private final FocusRepository focusRepository;

    public FocusSession startSession(CreateFocusSessionDTO createFocusSessionDTO) throws SQLException {
        return focusRepository.save(createFocusSessionDTO);
    }

    public void completeSession(String id, String userId) throws SQLException {
        if (!focusRepository.complete(id, userId)) {
            throw new FocusSessionNotFoundException(id);
        }
    }
}
