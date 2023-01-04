package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerNewCard implements Initializable, Observer {

    @FXML
    private ChoiceBox<String> menuDeckChoice;

    @FXML
    private TextArea question;

    @FXML
    private TextArea answer;

    @FXML
    private Button addCardOneDeck;

    @FXML
    private Button addCardManyDecks;

    private ModelFlashcard model;

    public ControllerNewCard(ModelFlashcard model) {
        this.model = model;
    }

    public ChoiceBox<String> getMenuDeckChoice() {
        return this.menuDeckChoice;
    }

    public TextArea getQuestion() {
        return this.question;
    }

    public TextArea getAnswer() {
        return this.answer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> deckNames = new ArrayList<>();
        for (Deck deck : this.model.getDeckList().getDeckList()) {
            System.out.println("Name : " + deck.getName());
            deckNames.add(deck.getName());
        }
        this.menuDeckChoice.getItems().addAll(deckNames);
        this.menuDeckChoice.setValue(deckNames.get(0));
    }

    public void switchToDeckList() {
        this.model.getViewChanger().setView("DeckList");
    }

    public void switchToCardList() {
        this.model.getViewChanger().setView("CardList");
    }

    public void addNewCard(String question, String answer) {
        Card card = new Card(question, answer);
        this.model.getCardList().addCard(card);
        this.model.getDeckList().searchDeckByName("aaa").addCard(card);
    }

    @Override
    public void reagir() {

    }


}
