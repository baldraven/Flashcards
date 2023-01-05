package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import javafx.fxml.Initializable;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceDialog;
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
        this.model.getViewChanger().ajouterObs("CardList", this);
    }

    public void displayCards() {
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
    public void addCardToDeck() {
        if (content.getSelectionModel().getSelectedIndex() == -1) return;
        int card_id = content.getSelectionModel().getSelectedIndex();
        this.model.setSelectedCard(model.getCardList().getCardList().get(card_id));
        ArrayList<String> deckNames = this.model.getDeckList().getDeckNames();
        if (deckNames.size() == 0) {
            return;
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(deckNames.get(0), deckNames);
        dialog.setTitle("TN's Flashcards");
        dialog.setHeaderText("Ajouter une carte à une pile");
        dialog.setContentText("Choisissez la pile à laquelle vous souhaitez ajouter la carte :\nQuestion : " + this.model.getSelectedCard().getQuestion() + "\nRéponse : " + this.model.getSelectedCard().getAnswer());
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            InsertApp app = new InsertApp();
            app.insertRelationCardsDecks(this.model.getSelectedCard().getQuestion(), result.get());

            this.model.getDeckList().searchDeckByName(result.get()).addCard(this.model.getSelectedCard());
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
    public void deleteCard(){
        if (content.getSelectionModel().getSelectedIndex() == -1) return;
        int card_id = content.getSelectionModel().getSelectedIndex(); //gets the index of the selected card in ListView
        this.model.setSelectedCard(this.model.getCardList().getCardList().get(card_id));

        String question = this.model.getSelectedCard().getQuestion();

        DeleteApp app = new DeleteApp();
        app.deleteCard(question);
        app.deleteCardInRelationWithQuestion(question);

        //System.out.println("Deleted card : " + question);
        for (Deck deck : this.model.getDeckList().getDeckList()) {
            deck.removeCardByQuestion(question);
        }

        model.getCardList().getCardList().remove(card_id);
        reagir();
    }


    @Override
    public void reagir() {
        this.displayCards();
    }
}
