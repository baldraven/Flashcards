package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.controller.ControllerDeckList;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.DeckList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDeckList implements Initializable {
    private DeckList deckList;

    @FXML
    private ListView content;


    public ControllerDeckList(DeckList deckList) {
        this.deckList = deckList;
        //this.deck.ajouterObs(this);
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Deck deck : deckList.getDeckList()) {
            Button button = new Button("Etudier");
            HBox hbox = new HBox();
            button.setOnAction(event -> toLearning(deck));
            Label label = new Label(deck.getName());
            hbox.getChildren().addAll(label, button);
            content.getItems().add(hbox);
        }                                    
    }

    public void toLearning(Deck deck){
        deck.setCurrentView("Learning");
    }
}
