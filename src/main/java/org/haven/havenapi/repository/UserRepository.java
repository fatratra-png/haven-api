package org.haven.havenapi.repository;

import org.haven.havenapi.config.DatabaseConnection;
import org.haven.havenapi.dto.CreateUserDTO;
import org.haven.havenapi.exception.DatabaseException;
import org.haven.havenapi.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository

public class UserRepository {
    public UserRepository() throws SQLException {
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("id"),
                rs.getString("user_name"),
                rs.getInt("age"),
                User.Gender.valueOf(rs.getString("gender"))
        );
    }

    public User create(CreateUserDTO createRequest) {
        String createQuery = "INSERT INTO users(user_name,age,gender) VALUES(?,?,?) RETURNING id,user_name,age,gender";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(createQuery);
        ) {
            ps.setString(1, createRequest.userName());
            ps.setInt(2, createRequest.age());
            ps.setString(3, createRequest.gender().name());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                throw new DatabaseException("Creationg user: no row returned");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Creation failed", e);
        }
    }

    public Optional<User> findById(String id) {
        String findUserByIdQuery = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(findUserByIdQuery);
        ) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding user with id: " + id, e);
        }
    }
}
