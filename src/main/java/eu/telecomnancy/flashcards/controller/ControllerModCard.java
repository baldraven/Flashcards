package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.application.Platform;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModCard extends AbstractControllerMenu implements Initializable, Observer {
    @FXML
    private TextArea question;
    @FXML
    private TextArea answer;

    private Card card;

    public ControllerModCard(ModelFlashcard model) {
        super(model);
        model.getViewChanger().ajouterObs(this);
    }

    public TextArea getQuestion() {
        return this.question;
    }

    public TextArea getAnswer() {
        return this.answer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void save() {
        String question = this.getQuestion().getText();
        String answer = this.getAnswer().getText();
        card.updateQuestion(question);
        card.updateAnswer(answer);
    }

    @Override
    public void reagir() {
        if (this.model.getSelectedCard() == null) return;
        this.card = this.model.getSelectedCard();
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer());
    }


}