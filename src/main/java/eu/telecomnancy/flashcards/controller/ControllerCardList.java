package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ControllerCardList extends AbstractControllerMenu implements Initializable, Observer {

    @FXML
    protected ListView<HBox> content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayCards();
    }
    
    public ControllerCardList(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs(this);
    }

    public void displayCards() {
        //System.out.println(this.model.getCardList());
        this.content.getItems().clear();
        for (Card card : this.model.getCardList().getCardList()) {
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

    @FXML
    public void switchToModCard(){
        if (content.getSelectionModel().getSelectedIndex() == -1) return;
        int card_id = content.getSelectionModel().getSelectedIndex(); //gets the index of the selected card in ListView
        model.setSelectedCard(model.getCardList().getCardList().get(card_id));
        model.getViewChanger().setView("ModCard");
    }

    @FXML
    public void deleteCard(){}


    @Override
    public void reagir() {
        this.displayCards();
    }
}
