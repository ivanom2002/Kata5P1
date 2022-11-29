package kata5p1;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Kata5P1 {

    public static void main(String[] args) {
        Loader loader = new EmailLoader(new FileLoader(new File("email.txt")));
        List<String> emails = loader.load();
        
        InsertIntoTable insertIntoTable = new InsertIntoTable();
        
        Connection connection = connect();
        createTableEmail(connection);
        closeConnection(connection);
        
        for (String email : emails) {
            insertIntoTable.insert(email);
        }
        insertIntoTable.closeConnection();
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

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) connection.close();
            System.out.println("Conexión a SQLite cerrada");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void createTableEmail(Connection connection) {
        try {
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS"
                    + " EMAIL(Id INTEGER PRIMARY KEY AUTOINCREMENT, Mail TEXT"
                    + " NOT NULL);");
            System.out.println("Tabla creada con éxito");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
