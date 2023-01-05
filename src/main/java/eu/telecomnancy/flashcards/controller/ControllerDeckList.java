package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ControllerDeckList extends AbstractControllerMenu implements Initializable, Observer {

    @FXML
    protected ListView<HBox> content;

    public ControllerDeckList(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(model.getDeckList());
        this.displayDecks();
    }

    public void displayDecks() {
        content.getItems().clear();
        for (Deck deck : model.getDeckList().getDeckList()) {
            HBox hbox = new HBox();

            hbox.setMinHeight(40);
            hbox.setMaxHeight(600);
            hbox.setSpacing(30);
            hbox.setPadding(new Insets(20, 0, 20, 0));
            hbox.setAlignment(Pos.CENTER);

            Button studyButton = new Button("Etudier");
            studyButton.setOnAction(action -> switchToLearning(deck));
            Button editButton = new Button("Modifier");
            editButton.setOnAction(action -> switchToDeckContent(deck));
            Button deleteButton = new Button("Supprimer");
            deleteButton.setOnAction(action -> deleteDeck(deck));
            // Problèmes si :
            // - Le dernier deck existant est supprimé
            // - Le deck actuel est supprimé

            Label deckName = new Label(deck.getName());
            deckName.setStyle("-fx-font-size: 18;");
            deckName.setPrefWidth(200);
            deckName.setAlignment(Pos.CENTER);
            deckName.setWrapText(true);
            deckName.setTooltip(new Tooltip(deckName.getText()));

            hbox.getChildren().addAll(deckName, studyButton, editButton, deleteButton);
            content.getItems().add(hbox);
        }
    }

    public void deleteDeck(Deck deck) {
        this.model.getDeckList().deleteDeckByName(deck.getName());
        deck.delete();
        this.model.getViewChanger().notifierObs();
    }

    @Override
    public void reagir() {
        this.displayDecks();
    }
}
