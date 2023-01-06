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
import javafx.scene.control.Alert;
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
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                contentDeck.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        List<String[]> contentCard = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileCard))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] container = line.split(",");
                System.out.println(container[0]);
                int count = 0;
                for (String str  : container) {
                    System.out.println(str);
                    String newStr = str.replace("\"", "");
                    System.out.println(newStr);
                    container[count] = newStr;
                    count++;
                }
                System.out.println(container[0]);
                contentCard.add(container);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TN's Flashcards");
            alert.setHeaderText("Dossier non reconnu");
            alert.setContentText("Le dossier selectionné est incorrect, veuillez recommencer.");
            alert.showAndWait();
        }

        InsertApp insertApp = new InsertApp();
        insertApp.insertDeck(contentDeck.get(0)[0].replace("\"", ""), contentDeck.get(0)[1].replace("\"", ""), Integer.valueOf(contentDeck.get(0)[2].replace("\"", "")), Integer.valueOf(contentDeck.get(0)[3].replace("\"", "")), Integer.valueOf(contentDeck.get(0)[4].replace("\"", "")), Integer.valueOf(contentDeck.get(0)[5].replace("\"", "")));
        Deck deck = new Deck(contentDeck.get(0)[0].replace("\"", ""), contentDeck.get(0)[1].replace("\"", ""), Integer.valueOf(contentDeck.get(0)[2].replace("\"", "")), Integer.valueOf(contentDeck.get(0)[3].replace("\"", "")), Integer.valueOf(contentDeck.get(0)[4].replace("\"", "")), Integer.valueOf(contentDeck.get(0)[5].replace("\"", "")));

        for (String[] rowCard : contentCard) {
            System.out.println(rowCard[0]);
            insertApp.insertCard(rowCard[0].replace("\"", ""), rowCard[1].replace("\"", ""), Double.valueOf(rowCard[2].replace("\"", "")), Double.valueOf(rowCard[3].replace("\"", "")), Long.valueOf(rowCard[4].replace("\"", "")), Integer.valueOf(rowCard[5].replace("\"", "")), Integer.valueOf(rowCard[6].replace("\"", "")), Integer.valueOf(rowCard[7].replace("\"", "")), Integer.valueOf(rowCard[8].replace("\"", "")));
            insertApp.insertRelationCardsDecks(rowCard[0].replace("\"", ""), contentDeck.get(0)[0].replace("\"", ""));
            Card card = new Card(rowCard[0].replace("\"", ""), rowCard[1].replace("\"", ""), Double.valueOf(rowCard[2].replace("\"", "")), Double.valueOf(rowCard[3].replace("\"", "")), Long.valueOf(rowCard[4]), Integer.valueOf(rowCard[5].replace("\"", "")), Integer.valueOf(rowCard[6].replace("\"", "")), Integer.valueOf(rowCard[7].replace("\"", "")), Integer.valueOf(rowCard[8].replace("\"", "")));
            model.getCardList().addCard(card);
            deck.addCard(card);
        }
        for(String name : model.getDeckList().getDeckNames())
        {
            if(name.contentEquals(contentDeck.get(0)[0].replace("\"", "")))
            {
                return;
            }
        }
        model.getDeckList().addDeck(deck);
    }
}
