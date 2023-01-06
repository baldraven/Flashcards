package eu.telecomnancy.flashcards.sql.connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.stage.DirectoryChooser;

public class ImportApp {
    
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

    public void importData(ModelFlashcard model) throws IOException {
        DirectoryChooser chooser = new DirectoryChooser();
        String selectedDirectory = chooser.showDialog(null).getName();

        String fileDeck = "data/"+selectedDirectory+"/"+selectedDirectory+".csv";
        String fileCard = "data/"+selectedDirectory+"/"+selectedDirectory+"Cartes.csv";

        List<String[]> contentDeck = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileDeck))) {
            String line = "";
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                contentDeck.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        List<String[]> contentCard = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileCard))) {
            String line = "";
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                contentCard.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        InsertApp insertApp = new InsertApp();
        insertApp.insertDeck(contentDeck.get(0)[0], contentDeck.get(0)[1], Integer.valueOf(contentDeck.get(0)[2]), Integer.valueOf(contentDeck.get(0)[3]), Integer.valueOf(contentDeck.get(0)[4]), Integer.valueOf(contentDeck.get(0)[5]));
        Deck deck = new Deck(contentDeck.get(0)[0], contentDeck.get(0)[1], Integer.valueOf(contentDeck.get(0)[2]), Integer.valueOf(contentDeck.get(0)[3]), Integer.valueOf(contentDeck.get(0)[4]), Integer.valueOf(contentDeck.get(0)[5]));

        for (String[] rowCard : contentCard) {
            insertApp.insertCard(rowCard[0], rowCard[1], Double.valueOf(rowCard[2]), Double.valueOf(rowCard[3]), Long.valueOf(rowCard[4]), Integer.valueOf(rowCard[5]), Integer.valueOf(rowCard[6]), Integer.valueOf(rowCard[7]), Integer.valueOf(rowCard[8]));
            insertApp.insertRelationCardsDecks(rowCard[0], contentDeck.get(1)[0]);
            Card card = new Card(rowCard[0], rowCard[1], Double.valueOf(rowCard[2]), Double.valueOf(rowCard[3]), Long.valueOf(rowCard[4]), Integer.valueOf(rowCard[5]), Integer.valueOf(rowCard[6]), Integer.valueOf(rowCard[7]), Integer.valueOf(rowCard[8]));
            model.getCardList().addCard(card);
            deck.addCard(card);
        }

        model.getDeckList().addDeck(deck);
    }
}
