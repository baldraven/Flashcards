package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.controller.ControllerDeckList;
import eu.telecomnancy.flashcards.model.CardList;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.DeckList;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDeckList implements Initializable{
    private ModelFlashcard model;
    @FXML
    private ListView content;


    public ControllerDeckList(ModelFlashcard model) {
        this.model = model;
    }

    public void switchToNewCard() {
        model.getViewChanger().setView("NewCard");
    }

    public void switchToCardList(ActionEvent event) {
        model.getViewChanger().setView("CardList");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(model.getDeckList());
        for (Deck deck : model.getDeckList().getDeckList()) {
            Button button = new Button("Etudier");
            HBox hbox = new HBox();
            button.setOnAction(action -> model.getViewChanger().setView("Learning"));
            Label label = new Label(deck.getName());
            hbox.getChildren().addAll(label, button);
            content.getItems().add(hbox);
        }                                    
    }
}
