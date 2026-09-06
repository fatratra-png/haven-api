package org.haven.havenapi.repository;

import org.haven.havenapi.config.DatabaseConnection;
import org.haven.havenapi.dto.CreateFocusSessionDTO;
import org.haven.havenapi.exception.DatabaseException;
import org.haven.havenapi.model.FocusSession;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

@Repository

public class FocusRepository {
    private FocusSession mapRow(ResultSet rs) throws SQLException {
        return new FocusSession(
                rs.getString("id"),
                rs.getString("user_id"),
                Duration.ofSeconds(rs.getLong("duration_seconds")),
                rs.getTimestamp("started_at").toLocalDateTime(),
                rs.getBoolean("completed")
        );
    }

    public FocusSession save(CreateFocusSessionDTO saveRequest) throws SQLException {
        String saveQuery = "INSERT INTO focus_sessions(user_id,duration_seconds)" +
                "VALUES(?,?)" +
                "RETURNING id,user_id,duration_seconds,started_at,completed;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(saveQuery)
        ) {
            ps.setString(1, saveRequest.userId());
            ps.setString(2, saveRequest.duration().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                throw new DatabaseException("Focus session creation returned no row");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during focus session registering", e);
        }
    }

    public boolean complete(String id, String userId) throws SQLException {
        String completeQuery = "UPDATE focus_sessions SET completed = true WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(completeQuery)
        ) {
            ps.setString(1, id);
            ps.setString(2, userId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during focus session completion", e);
        }
    }
}