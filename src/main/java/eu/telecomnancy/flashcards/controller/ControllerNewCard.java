package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class ControllerNewCard implements Observer {

    private Deck deck;

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

    public ControllerNewCard(Deck deck) {
        this.deck = deck;
        this.deck.ajouterObs(this);
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

    public void addNewCard(String question, String answer) {
        this.deck.addCard(new Card(question, answer));
    }

    @Override
    public void reagir() {

    }
}
