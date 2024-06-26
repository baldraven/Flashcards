package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ControllerCardList extends AbstractControllerMenu {

    @FXML
    protected ListView<HBox> content;
    @FXML
    private ImageView home;
    @FXML
    private ImageView add;
    @FXML
    private ImageView stack;
    @FXML
    private ImageView modify;
    @FXML
    private ImageView trash;

    @FXML
    private TextField searchBar;

    private ArrayList<Card> displayedCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayCards(this.model.getCardList().getCardList());
        Tooltip tooltip = new Tooltip("Retour à la liste de piles.");
        tooltip.install(home, tooltip);
        Tooltip tooltip1 = new Tooltip("Ajouter une carte");
        tooltip1.install(add, tooltip1);
        Tooltip tooltip2 = new Tooltip("Ajouter la carte selectionnée à une pile");
        tooltip2.install(stack, tooltip2);
        Tooltip tooltip3 = new Tooltip("Modifier la carte selectionnée");
        tooltip3.install(modify, tooltip3);
        Tooltip tooltip4 = new Tooltip("Supprimer la carte selectionnée");
        tooltip4.install(trash, tooltip4);
        this.content.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public ControllerCardList(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("CardList", this);
    }

    public void displayCards(ArrayList<Card> cards) {
        this.content.getItems().clear();
        for (Card card : cards) {
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
        ArrayList<String> deckNames = this.model.getDeckList().getDeckNames();
        if (deckNames.size() == 0) {
            return;
        }
        ArrayList<Card> selectedCardList = new ArrayList<>();

        for (HBox hbox : content.getSelectionModel().getSelectedItems()) { //We get all selected cards !!

            Label label = (Label) hbox.getChildren().get(0);
            String question = label.getText();
            Card card = model.getCardList().getCardByQuestion(question);

            selectedCardList.add(card);
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(deckNames.get(0), deckNames);
        dialog.setTitle("TN's Flashcards");
        dialog.setHeaderText("Ajouter une / des carte(s) à une pile");
        dialog.setContentText("Choisissez la pile à laquelle vous souhaitez ajouter la ou les cartes :");
        Optional<String> result = dialog.showAndWait();
        Deck selectedDeck = model.getDeckList().searchDeckByName(result.get());
        if (result.isPresent()) {
            for (Card card : selectedCardList)  {
                if (!selectedDeck.isQuestionInDeck(card.getQuestion()))
                   selectedDeck.addCard(card);
            }
        }
    }

    @FXML
    public void switchToModCard(){
        if (content.getSelectionModel().getSelectedIndex() == -1) return;
        String question = getQuestionFromList();
        Card card = model.getCardList().getCardByQuestion(question);

        model.setSelectedCard(card);
        model.getViewChanger().setView("ModCard");
    }

    public String getQuestionFromList() {
        Label label = (Label) content.getSelectionModel().getSelectedItem().getChildren().get(0);
        return label.getText();
    }

    @FXML
    public void deleteCard(){
        if (content.getSelectionModel().getSelectedIndex() == -1) return;
        String question = getQuestionFromList();
        Card card = model.getCardList().getCardByQuestion(question);

        DeleteApp app = new DeleteApp();
        app.deleteCard(question);
        app.deleteCardInRelationWithQuestion(question);

        for (Deck deck : this.model.getDeckList().getDeckList()) {
            deck.removeCardByQuestion(question);
        }

        model.getCardList().getCardList().remove(card);
        reagir();
    }

    @FXML
    public void researchCards() {
        //System.out.println("Search bar : " + this.searchBar.getText());
        if (this.searchBar.getText().length() == 0) {
            this.reagir();
        }
        ArrayList<Card> foundCards = new ArrayList<>();
        for (Card card : this.model.getCardList().getCardList()) {
            if (card.getQuestion().contains(this.searchBar.getText())) {
                foundCards.add(card);
            }
        }
        this.displayCards(foundCards);
    }

    @Override
    public void reagir() {
        this.displayCards(this.model.getCardList().getCardList());
        searchBar.clear();
    }
}
