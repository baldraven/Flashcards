package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
import eu.telecomnancy.flashcards.sql.connect.ImportApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerDeckList extends AbstractControllerMenu {

    @FXML
    protected ListView<HBox> content;
    @FXML
    private ImageView add;

    public ControllerDeckList(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("DeckList", this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayDecks();
        Tooltip tooltip1 = new Tooltip("Ajouter une pile");
        tooltip1.install(add, tooltip1);
    }

    public void displayDecks() {
        content.getItems().clear();
        for (Deck deck : model.getDeckList().getDeckList()) {
            HBox hbox = new HBox();

            hbox.setMinHeight(40);
            hbox.setMaxHeight(600);
            hbox.setSpacing(30);
            hbox.setPadding(new Insets(20, 0, 20, 0));
            hbox.setAlignment(Pos.CENTER);

            Button studyButton = new Button("Etudier");
            studyButton.setOnAction(action -> switchToLearning(deck));
            Button editButton = new Button("Modifier");
            editButton.setOnAction(action -> switchToDeckContent(deck));
            Button deleteButton = new Button("Supprimer");
            deleteButton.setOnAction(action -> deleteDeck(deck));

            Label deckName = new Label(deck.getName());
            deckName.setStyle("-fx-font-size: 18;");
            deckName.setPrefWidth(200);
            deckName.setAlignment(Pos.CENTER);
            deckName.setWrapText(true);
            deckName.setTooltip(new Tooltip(deck.getDescription()));

            hbox.getChildren().addAll(deckName, studyButton, editButton, deleteButton);
            content.getItems().add(hbox);
        }
    }

    public void deleteDeck(Deck deck) {
        //System.out.println("Deleted deck : " + deck.getName());
        DeleteApp app = new DeleteApp();
        for (Card card : deck.getDeck()) {
            app.deleteCardFromDeck(card.getQuestion(), deck.getName());
        }
        deck.delete();
        this.model.getDeckList().deleteDeckByName(deck.getName());
        this.reagir();
    }

    @FXML
    public void importer() throws IOException {
        ImportApp importApp = new ImportApp();
        importApp.importData(this.model);
    }

    @FXML
    public void accesParam()
    {
        this.model.getViewChanger().setView("Param");
    }

    @Override
    public void reagir() {
        this.displayDecks();
    }
}
