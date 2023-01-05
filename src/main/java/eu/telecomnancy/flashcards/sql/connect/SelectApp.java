package eu.telecomnancy.flashcards.sql.connect;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.DateStats;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.DeckList;
import eu.telecomnancy.flashcards.model.Param;

import java.sql.PreparedStatement;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp {

    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("SelectApp.connect: " + e.getMessage());
        }
        return conn;
    }

    
    /**
     * display all rows in the cards table
     */
    public void displayAllCards(){
        String sql = "SELECT question, answer FROM cards";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("question") +  "\t" + 
                                   rs.getString("answer"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.displayAllCards: " + e.getMessage());
        }
    }

    /**
     * select all rows in the cards table
     */
    public ArrayList<String> selectAllQuestionsCards(){
        String sql = "SELECT question, answer FROM cards";
        
        ArrayList<String> questionList = new ArrayList<String>();
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                questionList.add(rs.getString("question"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.displayAllCards: " + e.getMessage());
        }

        return questionList;
    }

    /**
     * display all rows in the deck table
     */
    public void displayAllDecks(){
        String sql = "SELECT name, description FROM decks";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("name") +  "\t" + 
                                   rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.displayAllDecks: " + e.getMessage());
        }
    }

    /**
     * select all rows in the deck table
     */
    public DeckList selectAllDecks(DeckList deckList){
        String sql = "SELECT name, description FROM decks";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                Deck deck = new Deck();
                deck.setName(rs.getString("name"));
                deck.setDescription(rs.getString("description"));

                deckList.addDeck(deck);
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.selectAllDecks: " + e.getMessage());
        }

        return deckList;
    }

    /**
     * Get the cards whith name of the deck
     * @param deck
     * @param name 
     */
    public Deck getCardsWithDeckName(Deck deck, String name){
        String sql = "SELECT question "
                   + "FROM relationCardsDecks WHERE name = ?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,name);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Card card = new Card();
                card.setQuestion(rs.getString("question"));
                deck.addCard(card);
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getCardsWithDeckName: " + e.getMessage());
        }

        return deck;
    }

    /**
     * Get the answer whith question of the card
     * @param card
     * @param question 
     */
    public Card getAnswerWithCardQuestion(Card card, String question){
        String sql = "SELECT answer "
                   + "FROM cards WHERE question = ?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,question);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                card.setAnswer(rs.getString("answer"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getAnswerWithCardQuestion: " + e.getMessage());
        }

        return card;
    }

    /**
     * Get the description whith name of the deck
     * @param deck
     * @param name 
     */
    public Deck getDescriptionWithDeckName(Deck deck, String name){
        String sql = "SELECT description "
                   + "FROM decks WHERE name = ?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,name);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                deck.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getDescriptionWithDeckName: " + e.getMessage());
        }

        return deck;
    }

    /**
     * Get the parameters of the app
     */
    public Param selectAllParam(Param param){
        String sql = "SELECT isSecond,oneTime,second "
                   + "FROM parameters WHERE parameterID = 0";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                param.setIsSecond((rs.getInt("isSecond")) == 1);
                param.setOneTime(rs.getInt("oneTime") == 1);
                param.setSecond(rs.getInt("second"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.selectAllParam: " + e.getMessage());
        }

        return param;
    }

    /**
     * Get the number of cards
     */
    public Integer selectCardCount(){
        String sql = "SELECT * "
                + "FROM cards";
        
                   try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            Integer count = 0;
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (SQLException e) {
            System.out.println("SelectApp.selectAllParam: " + e.getMessage());
        }

        return null;
    }

    /**
     * Get the number of decks
     */
    public Integer selectDeckCount(){
        String sql = "SELECT * "
                + "FROM decks";
        
                   try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            Integer count = 0;
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (SQLException e) {
            System.out.println("SelectApp.selectAllParam: " + e.getMessage());
        }

        return null;
    }


    /**
     * Get the description whith name of the deck
     * @param deck
     * @param name 
     */
    public Deck getDeckParameters(Deck deck, String name){
        String sql = "SELECT again,hard,good,easy "
                   + "FROM decks WHERE name = ?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,name);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                deck.setAgain(rs.getInt("again"));
                deck.setHard(rs.getInt("hard"));
                deck.setGood(rs.getInt("good"));
                deck.setEasy(rs.getInt("easy"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getDescriptionWithDeckName: " + e.getMessage());
        }

        return deck;
    }

    /**
     * Get the answer whith question of the card
     * @param card
     * @param question 
     */
    public Card getCardParameters(Card card, String question){
        String sql = "SELECT interval,ease,time,again,hard,good,easy "
                   + "FROM cards WHERE question = ?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,question);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                card.setInterval(rs.getInt("interval"));
                card.setEase(rs.getInt("ease"));
                card.setTimer(rs.getInt("time"));
                card.setAgain(rs.getInt("again"));
                card.setHard(rs.getInt("hard"));
                card.setGood(rs.getInt("good"));
                card.setEasy(rs.getInt("easy"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getAnswerWithCardQuestion: " + e.getMessage());
        }

        return card;
    }

    /**
     * Get the stats of a day
     * @param date
     */
    public DateStats getDeckDateStats(String date){
        String sql = "SELECT created,studied,again,hard,good,easy "
                   + "FROM dateStats WHERE date = ?";
        
        DateStats dateStats = new DateStats();

        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1, date);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                dateStats.setCreated(rs.getInt("created"));
                dateStats.setStudied(rs.getInt("studied"));
                dateStats.setAgain(rs.getInt("again"));
                dateStats.setHard(rs.getInt("hard"));
                dateStats.setGood(rs.getInt("good"));
                dateStats.setEasy(rs.getInt("easy"));
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getAnswerWithCardQuestion: " + e.getMessage());
        }

        return dateStats;
    }

    /**
     * Get the stats of a day
     */
    public ArrayList<DateStats> getAllStats(){
        String sql = "SELECT created,studied,again,hard,good,easy "
                   + "FROM dateStats";
        
        ArrayList<DateStats> statList = new ArrayList<DateStats>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                DateStats dateStats = new DateStats();

                dateStats.setCreated(rs.getInt("created"));
                dateStats.setStudied(rs.getInt("studied"));
                dateStats.setAgain(rs.getInt("again"));
                dateStats.setHard(rs.getInt("hard"));
                dateStats.setGood(rs.getInt("good"));
                dateStats.setEasy(rs.getInt("easy"));

                statList.add(dateStats);
            }
        } catch (SQLException e) {
            System.out.println("SelectApp.getAnswerWithCardQuestion: " + e.getMessage());
        }

        return statList;
    }
}