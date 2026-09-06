package org.haven.havenapi.exception;

public class FocusSessionNotFoundException extends RuntimeException {
    public FocusSessionNotFoundException(String id) {
        super("Focus session not found | doesn't belong to this user: " + id);
    }
}
