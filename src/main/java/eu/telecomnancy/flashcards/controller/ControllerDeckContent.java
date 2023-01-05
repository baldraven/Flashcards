package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
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

public class ControllerDeckContent extends AbstractControllerMenu implements Initializable, Observer {

    @FXML
    protected ListView<HBox> content;

    @FXML
    private Label deckNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayCards();
    }

    public ControllerDeckContent(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("DeckContent", this);
    }

    @FXML
    public void accesParam()
    {
        this.model.getViewChanger().setView("Param");
    }

    public void displayCards() {
        this.content.getItems().clear();
        if (this.model.getSelectedDeck() == null) {
            return;
        }
        for (Card card : this.model.getSelectedDeck().getDeck()) {
            System.out.println("Question : " + card.getQuestion());

            HBox hbox = new HBox();

            hbox.setMinHeight(40);
            hbox.setMaxHeight(600);
            hbox.setSpacing(30);
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

            //Button removeButton = new Button("Retirer");
            //removeButton.setOnAction(action -> removeCardInDeck(card));

            this.deckNameLabel.setText("Pile " + this.model.getSelectedDeck().getName());

            hbox.getChildren().addAll(questionLabel, answerLabel);
            this.content.getItems().add(hbox);
        }
    }

    @FXML
    public void removeCardInDeck() {
        if (content.getSelectionModel().getSelectedIndex() == -1) return;
        int card_id = content.getSelectionModel().getSelectedIndex();
        this.model.setSelectedCard(this.model.getSelectedDeck().getCard(card_id));
        //System.out.println("Selected card : " + this.model.getSelectedCard().getQuestion() + ", " + this.model.getSelectedCard().getAnswer());
        //System.out.println("Selected deck : " + this.model.getSelectedDeck().getName());

        DeleteApp app = new DeleteApp();
        app.deleteCardFromDeck(this.model.getSelectedCard().getQuestion(), this.model.getSelectedDeck().getName());
        //System.out.println("Question de la carte à retirer : " + this.model.getSelectedCard().getQuestion());
        this.model.getSelectedDeck().removeCardByQuestion(this.model.getSelectedCard().getQuestion());

        /*Label questionLabel = (Label) this.content.getSelectionModel().getSelectedItem().getChildren().get(0);
        String question = questionLabel.getText();
        System.out.println("Question : " + question);*/

        this.reagir();
    }

    @Override
    public void reagir() {
        this.displayCards();
    }
}
