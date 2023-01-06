package eu.telecomnancy.flashcards.sql.connect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;

public class ExportApp {
    
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
     * Export data from deck
     */
    public void export(){
        String sql = "SELECT * "
                   + "FROM cards";

        try {
            Path path = Paths.get("data/");
            Files.createDirectories(path);
            File file = new File("data/data.csv");

            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            
            List<String[]> data = new ArrayList<String[]>();

            try (Connection conn = this.connect();
                PreparedStatement pstmt  = conn.prepareStatement(sql)){
                
                //
                ResultSet rs  = pstmt.executeQuery();
                
                //Add columns name
                data.add(new String[] { "question","answer","interval","ease","time","again","hard","good","easy" } );
                
                // loop through the result set
                while (rs.next()) {
                    data.add(new String[] { rs.getString("question"), rs.getString("answer"), String.valueOf(rs.getInt("interval")), String.valueOf(rs.getInt("ease")), String.valueOf(rs.getInt("time")), String.valueOf(rs.getInt("again")), String.valueOf(rs.getInt("hard")), String.valueOf(rs.getInt("good")), String.valueOf(rs.getInt("easy")) });
                }
            } catch (SQLException e) {
                System.out.println("ExportApp.export: " + e.getMessage());
            }
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export data from deck
     */
    public void exportCards(String deckName, ArrayList<Card> cardList){
        try {
            Path path = Paths.get("data/"+deckName+"/");
            Files.createDirectories(path);
            File file = new File("data/"+deckName+"/"+deckName+"Cartes.csv");

            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            
            List<String[]> data = new ArrayList<String[]>();

            //Add columns name
            data.add(new String[] { "question","answer","interval","ease","time","again","hard","good","easy" } );
                
            for (Card card : cardList) {
                data.add(new String[] { card.getQuestion(), card.getAnswer(), String.valueOf(card.getInterval()), String.valueOf(card.getEase()), String.valueOf(card.getTimer()), String.valueOf(card.getAgain()), String.valueOf(card.getHard()), String.valueOf(card.getGood()), String.valueOf(card.getEasy()) } );
            }

            writer.writeAll(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export data from deck
     */
    public void exportDeck(Deck deck){
        try {
            Path path = Paths.get("data/"+deck.getName()+"/");
            Files.createDirectories(path);
            File file = new File("data/"+deck.getName()+"/"+deck.getName()+".csv");

            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            
            List<String[]> data = new ArrayList<String[]>();

            //Add columns name
            data.add(new String[] { "name","description","again","hard","good","easy" } );
            
            data.add(new String[] { deck.getName(), deck.getDescription(), String.valueOf(deck.getAgain()), String.valueOf(deck.getHard()), String.valueOf(deck.getGood()), String.valueOf(deck.getEasy()) } );

            writer.writeAll(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
