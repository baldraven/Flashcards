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
        String url = "jdbc:sqlite:database.db";
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
     * @param interval
     * @param ease
     * @param time
     * @param again
     * @param hard
     * @param good
     * @param easy
     */
    public void insertCard(String question, String answer, double interval, double ease, long time, Integer again, Integer hard, Integer good, Integer easy) {
        String sql = "INSERT INTO cards(question,answer,interval,ease,time,again,hard,good,easy) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, answer);
            pstmt.setDouble(3, interval);
            pstmt.setDouble(4, ease);
            pstmt.setInt(5, (int)time);
            pstmt.setInt(6, again);
            pstmt.setInt(7, hard);
            pstmt.setInt(8, good);
            pstmt.setInt(9, easy);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("InsertApp.insertCard: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the cards table
     *
     * @param name
     * @param description
     */
    public void insertDeck(String name, String description, Integer again, Integer hard, Integer good, Integer easy) {
        String sql = "INSERT INTO decks(name,description,again,hard,good,easy) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, again);
            pstmt.setInt(4, hard);
            pstmt.setInt(5, good);
            pstmt.setInt(6, easy);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("InsertApp.insertDeck: " + e.getMessage());
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
            //System.out.println("InsertApp.insertRelationCardsDecks: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the parameters table
     *
     * @param parameterID
     * @param isSecond
     * @param oneTime
     * @param second
     */
    public void insertParameters(Integer parameterID, Boolean isSecond, Boolean oneTime, Integer second) {
        String sql = "INSERT INTO parameters(parameterID,isSecond,oneTime,second) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, parameterID);
            pstmt.setInt(2, isSecond ? 1 : 0);
            pstmt.setInt(3, oneTime ? 1 : 0);
            pstmt.setInt(4, second);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("InsertApp.insertParameters: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the date stats table
     *
     * @param date
     * @param created
     * @param studied
     * @param again
     * @param hard
     * @param good
     * @param easy
     */
    public void insertStats(String date, Integer created, Integer studied, Integer again, Integer hard, Integer good, Integer easy) {
        String sql = "INSERT INTO dateStats(date,created,studied,again,hard,good,easy) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setInt(2, created);
            pstmt.setInt(3, studied);
            pstmt.setInt(4, again);
            pstmt.setInt(5, hard);
            pstmt.setInt(6, good);
            pstmt.setInt(7, easy);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //System.out.println("InsertApp.insertStats: " + e.getMessage());
        }
    }
}