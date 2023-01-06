package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
import eu.telecomnancy.flashcards.sql.connect.ExportApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDeckContent extends AbstractControllerMenu {

    @FXML
    protected ListView<HBox> content;
    @FXML
    private ImageView home;
    @FXML
    private ImageView trash;
    @FXML
    private ImageView rename;

    @FXML
    private Label deckNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayCards();
        Tooltip tooltip = new Tooltip("Retour à la liste de piles.");
        tooltip.install(home, tooltip);
        Tooltip tooltip3 = new Tooltip("Modifier la description de la pile");
        tooltip3.install(rename, tooltip3);
        Tooltip tooltip4 = new Tooltip("Supprimer la carte de la pile");
        tooltip4.install(trash, tooltip4);
    }

    public ControllerDeckContent(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("DeckContent", this);
    }

    public void displayCards() {
        this.content.getItems().clear();
        if (this.model.getSelectedDeck() == null) {
            return;
        }
        this.deckNameLabel.setText("Pile " + this.model.getSelectedDeck().getName());
        for (Card card : this.model.getSelectedDeck().getDeck()) {
            //System.out.println("Question : " + card.getQuestion());

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

    @FXML
    public void descChanger() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("TN's Flashcards");
        dialog.setHeaderText("Renommer la description de la pile "+ this.model.getSelectedDeck().getName());
        dialog.setContentText("Saisissez la description de la pile : ");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
        {
            this.model.getSelectedDeck().setDescription(result.get());
            if(result.get().length() < 1)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TN's Flashcards");
                alert.setHeaderText("Description non saisie");
                alert.setContentText("Vous n'avez pas saisi de description.");
                alert.showAndWait();
                return; 
            }
        }
    }

    @FXML
    public void exporter() {
        ExportApp exportApp = new ExportApp();
        exportApp.exportCards(this.model.getSelectedDeck().getName(), this.model.getSelectedDeck().getDeck());
        exportApp.exportDeck(this.model.getSelectedDeck());
    }

    @Override
    public void reagir() {
        this.displayCards();
    }
}
