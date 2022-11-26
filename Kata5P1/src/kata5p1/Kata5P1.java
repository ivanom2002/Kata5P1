package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Kata5P1 {

    public static void main(String[] args) {
        Connection connection = connect();
        selectAll(connection);
        closeConnection(connection);
    }

    private static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:KATA5.db");
            System.out.println("Conexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private static void selectAll(Connection connection) {
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM PEOPLE");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + 
                                   rs.getString("Nombre") + "\t" +
                                   rs.getString("Apellidos") + "\t" +
                                   rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) connection.close();
            System.out.println("Conexión a SQLite cerrada");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
