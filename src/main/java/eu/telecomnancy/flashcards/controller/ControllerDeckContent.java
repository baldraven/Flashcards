package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDeckContent implements Initializable, Observer {

    private ModelFlashcard model;

    @FXML
    private ListView<HBox> content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayCards();
    }

    public ControllerDeckContent(ModelFlashcard model) {
        this.model = model;
        this.model.getViewChanger().ajouterObs(this);
    }

    @FXML
    public void quit()
    {
        Platform.exit();
    }

    public void switchToDeckList() {
        this.model.getViewChanger().setView("DeckList");
    }

    public void switchToCardList() {
        this.model.getViewChanger().setView("CardList");
    }

    public void displayCards() {
        this.content.getItems().clear();
        for (Card card : this.model.getDeckList().searchDeckByName("Math").getDeck()) {
            HBox hbox = new HBox();

            hbox.setMinHeight(40);
            hbox.setMaxHeight(600);
            hbox.setSpacing(60);
            hbox.setPadding(new Insets(20, 0, 20, 0));
            hbox.setAlignment(Pos.CENTER);

            Label questionLabel = new Label(card.getQuestion());
            Label answerLabel = new Label(card.getAnswer());

            questionLabel.setStyle("-fx-font-size: 18;");
            questionLabel.setPrefWidth(240);
            questionLabel.setAlignment(Pos.CENTER);
            questionLabel.setWrapText(true);
            questionLabel.setTooltip(new Tooltip(questionLabel.getText()));

            answerLabel.setStyle("-fx-font-size: 18;");
            answerLabel.setPrefWidth(240);
            answerLabel.setAlignment(Pos.CENTER);
            answerLabel.setWrapText(true);
            answerLabel.setTooltip(new Tooltip(answerLabel.getText()));

            hbox.getChildren().addAll(questionLabel, answerLabel);
            this.content.getItems().add(hbox);
        }
    }

    @Override
    public void reagir() {
        this.displayCards();
    }
}
