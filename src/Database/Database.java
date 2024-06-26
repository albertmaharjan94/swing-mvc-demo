/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Database;
import java.sql.*;

/**
 *
 * @author digitech
 */
public interface Database {
    Connection openConnection(); // Propagate exception for callers to handle.
    void closeConnection(Connection conn); // Propagate exception.
    ResultSet runQuery(Connection conn, String query); // Propagate exception.
    int executeUpdate(Connection conn, String query) ; // Propagate exception.
}
