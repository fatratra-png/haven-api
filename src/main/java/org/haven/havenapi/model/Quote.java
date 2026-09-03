package org.haven.havenapi.model;

import java.time.LocalDate;

public record Quote(String id, String text, String author, LocalDate date) {
}
