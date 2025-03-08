/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class Connection<T> {
    private static final String URL = "jdbc:mysql://localhost:3306/qlpk1";
    private static final String USER = "root";
    private static final String PASSWORD = "123321";
    private static final Logger LOGGER = Logger.getLogger(Connection.class.getName());

    private java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int insert(String query, Object... params) {
        int generatedId = -1;
        java.sql.Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                setParameters(stmt, params);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = generatedKeys.getInt(1);
                        }
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Insert operation failed, rolling back transaction: " + e.getMessage(), e);
            rollbackTransaction(conn);
        } finally {
            closeConnection(conn);
        }

        return generatedId;
    }

    public int updateOrDelete(String query, Object... params) {
        int rowsAffected = 0;
        java.sql.Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                setParameters(stmt, params);
                rowsAffected = stmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Update/Delete operation failed, rolling back transaction: " + e.getMessage(), e);
            rollbackTransaction(conn);
        } finally {
            closeConnection(conn);
        }

        return rowsAffected;
    }

    public T fetchOne(String query, Function<ResultSet, T> mapper, Object... params) {
        T result = null;

        try (java.sql.Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            setParameters(stmt, params);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = mapper.apply(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Fetch one operation failed: " + e.getMessage(), e);
        }

        return result;
    }

    public List<T> fetchAll(String query, Function<ResultSet, T> mapper, Object... params) {
        List<T> results = new ArrayList<>();

        try (java.sql.Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            setParameters(stmt, params);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapper.apply(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Fetch all operation failed: " + e.getMessage(), e);
        }

        return results;
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    private void rollbackTransaction(java.sql.Connection conn) {
        if (conn != null) {
            try {
                conn.rollback(); 
                LOGGER.log(Level.INFO, "Transaction rolled back successfully.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Rollback failed: " + e.getMessage(), e);
            }
        }
    }

    private void closeConnection(java.sql.Connection conn) {
        if (conn != null) {
            try {
                try (conn) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to close connection: " + e.getMessage(), e);
            }
        }
    }
}
