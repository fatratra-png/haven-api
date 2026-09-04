package org.haven.havenapi.model;

public record User(String id, String username, int age, Gender gender) {
    public enum Gender {
        MALE, FEMALE
    }
}
