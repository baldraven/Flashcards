package eu.telecomnancy.flashcards.sql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class InsertApp {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("InsertApp.connect: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the cards table
     *
     * @param question
     * @param answer
     */
    public void insertCard(String question, String answer) {
        String sql = "INSERT INTO cards(question,answer) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, answer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("InsertApp.insertCard: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the cards table
     *
     * @param name
     * @param description
     */
    public void insertDeck(String name, String description) {
        String sql = "INSERT INTO decks(name,description) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("InsertApp.insertDeck: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the user table
     *
     * @param name
     * @param score
     */
    public void insertUser(String name, Integer score) {
        String sql = "INSERT INTO user(name,score) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("InsertApp.insertUser: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the relation cards/decks table
     *
     * @param question
     * @param name
     */
    public void insertRelationCardsDecks(String question, String name) {
        String sql = "INSERT INTO relationCardsDecks(question,name) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("InsertApp.insertRelationCardsDecks: " + e.getMessage());
        }
    }
}