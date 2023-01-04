package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.application.Platform;

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

    @FXML
    public void quit()
    {
        Platform.exit();
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

    public String getSelectedDeck() {
        return this.menuDeckChoice.getValue();
    }

    public void switchToDeckList() {
        this.model.getViewChanger().setView("DeckList");
    }

    public void switchToCardList() {
        this.model.getViewChanger().setView("CardList");
    }

    public void addNewCard() {

        String question = this.getQuestion().getText();
        String answer = this.getAnswer().getText();

        Card card = new Card(question, answer, 320, -1, 0);
        System.out.println(
                "Question : " + question +
                "\nRéponse : " + answer +
                "\nDeck : " + this.getSelectedDeck()
        );

        if (!this.model.getCardList().getQuestions().contains(question)) {
            System.out.println("Ajout dans la card list");
            this.model.getCardList().addCard(card);
        }

        if (!this.model.getDeckList().searchDeckByName(this.getSelectedDeck()).getQuestionsFromDeck().contains(question)) {
            System.out.println("Ajout dans le deck " + this.getSelectedDeck());
            this.model.getDeckList().searchDeckByName(this.getSelectedDeck()).addCard(card);
        }
    }

    @Override
    public void reagir() {

    }


}
