package eu.telecomnancy.flashcards.sql.tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Parameters {
    
    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS parameters (\n"
                + "	parameterID integer PRIMARY KEY NOT NULL AUTO_INCREMENT,\n"
                + "	isSecond integer,\n"
                + "	oneTime integer,\n"
                + "	second integer\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
