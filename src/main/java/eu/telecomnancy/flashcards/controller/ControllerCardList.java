package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class ControllerCardList implements Initializable{

    private ModelFlashcard model;

    @FXML
    private ListView content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(model.getCardList());
        for (Card card : model.getCardList().getCardList()) {
            HBox hbox = new HBox();
            Label label1 = new Label(card.getAnswer());
            Label label2 = new Label(card.getAnswer());
            hbox.getChildren().addAll(label1, label2);
            content.getItems().add(hbox);
        }
    }
    
    public ControllerCardList(ModelFlashcard model) {
        this.model = model;
    }

    public void switchToDeckList() {
        model.getViewChanger().setView("DeckList");
    }

    public void switchToNewCard() {
        model.getViewChanger().setView("NewCard");
    }
}
