package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNewDeck extends AbstractControllerMenu {

    @FXML
    private TextField deckName;

    @FXML
    private TextArea deckDescription;

    @FXML
    private ImageView home;
    
    @FXML
    private ImageView add;

    public ControllerNewDeck(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("NewDeck", this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.reagir();
        Tooltip tooltip = new Tooltip("Retour à la liste de piles.");
        tooltip.install(home, tooltip);
        Tooltip tooltip1 = new Tooltip("Ajouter une pile");
        tooltip1.install(add, tooltip1);
    }

    @FXML
    public void accesParam()
    {
        this.model.getViewChanger().setView("Param");
    }

    public void createDeck() {
        String name = this.deckName.getText().trim();
        String description = this.deckDescription.getText().trim();
        if (name.length() == 0 || description.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TN's Flashcards");
            alert.setHeaderText("Nom de pile et/ou description non saisi(e)");
            alert.setContentText("Vous n'avez pas saisi de nom de pile et/ou de description.\nVeuillez remplir les deux champs ci-dessous.\nLes noms uniquement composés d'espaces ne sont pas acceptés.");
            alert.showAndWait();
            return;
        }
        String legalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
        for (int i = 0; i < name.length(); i++) {
            if (legalChars.indexOf(name.charAt(i)) == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TN's Flashcards");
                alert.setHeaderText("Nom de pile incorrect saisi");
                alert.setContentText("Vous avez saisi un nom de pile interdit.\nLes caractères autorisés sont :\n\nabcdefghijklmnopqrstuvwxyz\nABCDEFGHIJKLMNOPQRSTUVWXYZ\n0123456789_");
                alert.showAndWait();
                return;
            }
        }
        //System.out.println(this.model.getDeckList().searchDeckByName(name));
        if (this.model.getDeckList().searchDeckByName(name) == null) { // Le nom de la pile est libre
            InsertApp app = new InsertApp();
            app.insertDeck(name, description, 0, 0, 0, 0);

            Deck newDeck = new Deck(name, description, 0, 0, 0, 0);
            this.model.getDeckList().getDeckList().add(newDeck);
        }
        this.switchToDeckList();
    }

    @Override
    public void reagir() {
        this.deckName.clear();
        this.deckDescription.clear();
    }
}
