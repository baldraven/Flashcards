package eu.telecomnancy.flashcards.sql.tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CardsStats {
    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/database.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS cardsStats (\n"
                + "	question text PRIMARY KEY,\n"
                + "	name text PRIMARY KEY,\n"
                + "	again integer,\n"
                + "	hard integer,\n"
                + "	good integer,\n"
                + "	easy integer,\n"
                + " FOREIGN KEY (name) REFERENCES relationCardsDecks(name),\n"
                + " FOREIGN KEY (question) REFERENCES relationCardsDecks(question)\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
    }
}
