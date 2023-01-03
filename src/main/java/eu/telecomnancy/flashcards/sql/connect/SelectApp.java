package eu.telecomnancy.flashcards.sql.connect;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eu.telecomnancy.flashcards.objects.Card;
import eu.telecomnancy.flashcards.objects.Deck;
import eu.telecomnancy.flashcards.objects.DeckList;

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
        String url = "jdbc:sqlite:db/database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }

        return deckList;
    }

    /**
     * Get the cards whith name of the deck
     * @param capacity 
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
            System.out.println(e.getMessage());
        }

        return deck;
    }
}