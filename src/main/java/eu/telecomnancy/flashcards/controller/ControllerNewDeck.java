package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNewDeck extends AbstractControllerMenu implements Initializable, Observer {

    @FXML
    private TextField deckName;

    @FXML
    private TextArea deckDescription;

    public ControllerNewDeck(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("NewDeck", this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.deckName.clear();
        this.deckDescription.clear();
    }

    @FXML
    public void accesParam()
    {
        this.model.getViewChanger().setView("Param");
    }

    public void createDeck() {
        String name = this.deckName.getText();
        String description = this.deckDescription.getText();
        System.out.println(this.model.getDeckList().searchDeckByName(name));
        if (this.model.getDeckList().searchDeckByName(name) == null) { // Le nom de la pile est libre
            InsertApp app = new InsertApp();
            app.insertDeck(name, description, 0, 0, 0, 0);

            Deck newDeck = new Deck(name, description, 0, 0, 0, 0);
            this.model.getDeckList().getDeckList().add(newDeck);
        }
        this.switchToDeckList();
    }

    @Override
    public void reagir() {

    }
}
