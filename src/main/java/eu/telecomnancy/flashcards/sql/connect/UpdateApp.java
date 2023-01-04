package eu.telecomnancy.flashcards.sql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class UpdateApp {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Update data of a card specified by the question
     *
     * @param oldQuestion question of the card before change
     * @param newQuestion question of the card after change
     * @param answer answer of the card
     */
    public void updateCardQuestionAnswer(String oldQuestion, String newQuestion, String answer) {
        String sql = "UPDATE cards SET question = ? , "
                + "answer = ? "
                + "WHERE question = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, newQuestion);
            pstmt.setString(2, answer);
            pstmt.setString(3, oldQuestion);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update data of a card specified by the question
     *
     * @param question question of the card
     * @param newQuestion question of the card after change
     * @param answer answer of the card
     */
    public void updateCardParameters(String question, double ease, long time, double interval, Integer again, Integer hard, Integer good, Integer easy) {
        String sql = "UPDATE cards SET ease = ? , "
                + "time = ? "
                + "interval = ? "
                + "again = ? "
                + "hard = ? "
                + "good = ? "
                + "easy = ? "
                + "WHERE question = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setFloat(1, (float)ease);
            pstmt.setInt(2, (int)time);
            pstmt.setFloat(3, (float)interval);
            pstmt.setInt(4, again);
            pstmt.setInt(5, hard);
            pstmt.setInt(6, good);
            pstmt.setInt(7, easy);
            pstmt.setString(8, question);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update data of a deck specified by the name
     *
     * @param oldName name of the deck before change
     * @param newName name of the deck after change
     * @param description description of the deck
     */
    public void updateDeck(String oldName, String newName, String description) {
        String sql = "UPDATE decks SET name = ? , "
                + "description = ? "
                + "WHERE name = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, newName);
            pstmt.setString(2, description);
            pstmt.setString(3, oldName);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
