package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import eu.telecomnancy.flashcards.sql.connect.UpdateApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNewDeck extends AbstractControllerMenu {

    public ControllerNewDeck(ModelFlashcard model) {
        super(model);
    }

    @FXML
    private TextField deckName;

    @FXML
    private TextArea deckDescription;

    public void createDeck() {
        String name = this.deckName.getText();
        String description = this.deckDescription.getText();
        System.out.println(this.model.getDeckList().searchDeckByName(name));
        if (this.model.getDeckList().searchDeckByName(name) == null) { // Le nom de la pile est libre
            InsertApp app = new InsertApp();
            app.insertDeck(name, description, 0, 0, 0, 0);
        }
    }
}
