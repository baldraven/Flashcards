package eu.telecomnancy.flashcards.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.controller.ControllerLearning;

public class ViewLearning implements Observer
{
    @FXML
    public Button repButton;
    @FXML
    public Label Rep;
    @FXML
    public GridPane gridP;
    @FXML
    public Label Ques;

    private Deck deck;
    private ControllerLearning Controler;

    public void ViewLearning(Deck deck)
    {
        this.deck = deck;
        Controler = new ControllerLearning(deck);
        this.reagir();
    } 
    @FXML
    public void AffRep()
    {
        Controler.AfficherRep(Rep, gridP);
    }
    public void reagir()
    {
        Controler.reagir(Rep, gridP, Ques);
    }
}