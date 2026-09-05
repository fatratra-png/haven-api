package org.haven.havenapi.repository;

import org.haven.havenapi.config.DatabaseConnection;
import org.haven.havenapi.dto.CreateMoodEntryDTO;
import org.haven.havenapi.exception.DatabaseException;
import org.haven.havenapi.model.Mood;
import org.haven.havenapi.model.MoodEntry;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MoodRepository {

    private MoodEntry mapRow(ResultSet rs) throws SQLException {
        return new MoodEntry(
                rs.getString("user_id"),
                Mood.valueOf(rs.getString("mood")),
                rs.getString("note"),
                rs.getTimestamp("time_stamp").toLocalDateTime()
        );
    }

    public MoodEntry insert(CreateMoodEntryDTO insertRequest) throws SQLException {
        String insertQuery = "INSERT INTO mood_entries(user_id,mood,note,) " +
                "VALUES(?, ?, ?)" +
                "RETURNING id,user_id,mood,note,time_stamp";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery)
        ) {
            ps.setString(1, insertRequest.userId());
            ps.setString(2, insertRequest.mood().name());
            ps.setString(3, insertRequest.note());

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) return mapRow(rs);
                throw new DatabaseException("Mood entry creation returned no now");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during mood entry registering", e);
        }
    }

    public List<MoodEntry> findByUserAndDate(String userId, LocalDate date) throws SQLException {
        String findByUserAndDateQuery = "SELECT * FROM mood_entries" +
                "WHERE user_id = ? AND time_stamp = ?" +
                "ORDER BY time_stamp ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(findByUserAndDateQuery);
        ) {
            ps.setString(1, userId);
            ps.setDate(2, Date.valueOf(date));

            try (ResultSet rs = ps.executeQuery()) {
                List<MoodEntry> entries = new ArrayList<>();
                while (rs.next()) entries.add(mapRow(rs));

                return entries;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during mood entries lookup", e);
        }
    }
}