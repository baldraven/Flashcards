package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModCard extends AbstractControllerMenu {
    @FXML
    private TextArea question;
    @FXML
    private TextArea answer;
    @FXML
    private ImageView home;
    @FXML
    private ImageView enreIcon;
    @FXML
    private ImageView retour;

    private Card card;

    public ControllerModCard(ModelFlashcard model) {
        super(model);
        model.getViewChanger().ajouterObs("ModCard", this);
    }

    public TextArea getQuestion() {
        return this.question;
    }

    public TextArea getAnswer() {
        return this.answer;
    }

    @FXML
    public void accesParam()
    {
        this.model.getViewChanger().setView("Param");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip tooltip = new Tooltip("Retour à la liste de piles.");
        tooltip.install(home, tooltip);
        Tooltip tooltip2 = new Tooltip("Enregistrer les modifications");
        tooltip.install(enreIcon, tooltip2);
        Tooltip tooltip3 = new Tooltip("Annuler les modifications");
        tooltip.install(retour, tooltip3);
    }

    public void save() {
        String question = this.getQuestion().getText();
        String answer = this.getAnswer().getText();
        card.updateQuestion(question);
        card.updateAnswer(answer);
        this.switchToCardList();
    }

    @Override
    public void reagir() {
        if (this.model.getSelectedCard() == null) return;
        this.card = this.model.getSelectedCard();
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer());
    }


}
