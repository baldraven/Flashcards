package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerDeckList extends AbstractControllerMenu implements Initializable, Observer {

    @FXML
    protected ListView<HBox> content;

    public ControllerDeckList(ModelFlashcard model) {
        super(model);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(model.getDeckList());
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
            Button deleteButton = new Button("Supprimer");

            Label deckName = new Label(deck.getName());
            deckName.setStyle("-fx-font-size: 18;");
            deckName.setPrefWidth(200);
            deckName.setAlignment(Pos.CENTER);
            deckName.setWrapText(true);
            deckName.setTooltip(new Tooltip(deckName.getText()));

            hbox.getChildren().addAll(deckName, studyButton, editButton, deleteButton);
            content.getItems().add(hbox);
        }                              
    }

    @Override
    public void reagir(){}
}
