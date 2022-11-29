package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class InsertIntoTable {
        
    Connection connection = null;

    public InsertIntoTable() {
        connection = this.connect();
    }
    
    private Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:KATA5.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
    public void insert(String email) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO"
                    + " EMAIL(Mail) VALUES(?)");
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
