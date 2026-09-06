package org.haven.havenapi.repository;

import org.haven.havenapi.config.DatabaseConnection;
import org.haven.havenapi.dto.CreateJournalEntryDTO;
import org.haven.havenapi.exception.DatabaseException;
import org.haven.havenapi.model.JournalEntry;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JournalRepository {
    private JournalEntry mapRow(ResultSet rs) throws SQLException {
        return new JournalEntry(
                rs.getString("id"),
                rs.getString("user_id"),
                rs.getString("content"),
                rs.getTimestamp("times_tamp").toLocalDateTime()
        );
    }

    public JournalEntry insert(CreateJournalEntryDTO insertRequest) {
        String insertQuery = "INSERT INTO journal_entries(user_id,content) " +
                "VALUES(?,?)" +
                "RETURNING id,user_id,content,time_stamp";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery);
        ) {
            ps.setString(1, insertRequest.userId());
            ps.setString(2, insertRequest.content());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                throw new DatabaseException("Journal entry creation returned no row");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during journal entry registering", e);
        }
    }

    public List<JournalEntry> findByUserAndDate(String userId, LocalDate date) {
        String findByUserAndDateQuery = "SELECT * FROM journal_entries" +
                "WHERE user_id = ? AND time_stamp = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(findByUserAndDateQuery)
        ) {
            ps.setString(1, userId);
            ps.setDate(2, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                List<JournalEntry> entries = new ArrayList<>();
                if (rs.next()) {
                    entries.add(mapRow(rs));
                }
                return entries;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during journal entry lookup", e);
        }
    }

}
