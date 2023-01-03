package eu.telecomnancy.flashcards.view;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.controller.ControllerNewCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class ViewNewCard implements Observer {

    private ControllerNewCard controllerNewCard;

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

    public ViewNewCard(ControllerNewCard controllerNewCard) {
        this.controllerNewCard = controllerNewCard;

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
    public void reagir() {

    }
}
