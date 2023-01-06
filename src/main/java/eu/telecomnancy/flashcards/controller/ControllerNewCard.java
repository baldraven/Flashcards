package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerNewCard extends AbstractControllerMenu {

    @FXML
    private ChoiceBox<String> menuDeckChoice;

    @FXML
    private TextArea question;

    @FXML
    private TextArea answer;

    @FXML
    private ImageView home;

    @FXML
    private ImageView add;

    public ControllerNewCard(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("NewCard", this);
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
    public void initialize(URL location, ResourceBundle resources) {
        this.loadDeckNames();
        Tooltip tooltip = new Tooltip("Retour à la liste de piles.");
        tooltip.install(home, tooltip);
        Tooltip tooltip1 = new Tooltip("Ajouter une carte");
        tooltip1.install(add, tooltip1);
    }

    public void loadDeckNames() {
        this.menuDeckChoice.getItems().clear();
        ArrayList<String> deckNames = new ArrayList<>();
        for (Deck deck : this.model.getDeckList().getDeckList()) {
            //System.out.println("Name : " + deck.getName());
            deckNames.add(deck.getName());
        }
        this.menuDeckChoice.getItems().addAll(deckNames);
        if (deckNames.size() > 0) {
            this.menuDeckChoice.setValue(deckNames.get(0));
        }
    }

    public String getSelectedDeck() {
        return this.menuDeckChoice.getValue();
    }

    public void addNewCard() {

        String question = this.getQuestion().getText();
        String answer = this.getAnswer().getText();

        Card card = new Card(question, answer, 230, -1, 0, 0, 0, 0, 0);
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
        this.loadDeckNames();
    }


}
