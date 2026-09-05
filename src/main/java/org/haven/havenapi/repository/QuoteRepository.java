package org.haven.havenapi.repository;

import org.haven.havenapi.config.DatabaseConnection;
import org.haven.havenapi.exception.DatabaseException;
import org.haven.havenapi.model.Quote;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class QuoteRepository {
    private Quote mapRow(ResultSet rs) throws SQLException {
        return new Quote(
                rs.getString("id"),
                rs.getString("text"),
                rs.getString("author"),
                rs.getDate("date").toLocalDate()
        );
    }

    public Optional<Quote> findByDate(LocalDate date) {
        String findByDateQuery = "SELECT * FROM quotes WHERE date = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(findByDateQuery);
        ) {
            ps.setDate(1, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during quote search", e);
        }
    }

    public Quote save(String text, String author, LocalDate date) {
        String saveQuery = "INSERT INTO quotes(text, author, date) " +
                "VALUES (?, ?, ?) ON CONFLICT(date) DO NOTHING " +
                "RETURNING id,text,author,date;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(saveQuery);
        ) {
            ps.setString(1, text);
            ps.setString(2, author);
            ps.setDate(3, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return findByDate(date).orElseThrow(() -> new DatabaseException("Quote not found due to insertion conflict"));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error encountered during quote registering", e);
        }
    }

}
