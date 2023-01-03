package eu.telecomnancy.flashcards.controller;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Random;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
public class ControllerLearning implements Observer
{
    @FXML
    private Button repButton;
    @FXML
    private Label Rep;
    @FXML
    private GridPane gridP;
    @FXML
    private Label Ques;
    private Deck deck;
    private Card card;

    public ControllerLearning(Deck deck)
    {
        this.deck = deck;
        this.reagir();
    }
    @FXML
    public void AffRep(ActionEvent event)
    {
        Rep.setVisible(true);
        gridP.setVisible(true);
        repButton.setVisible(false);
    }
    @FXML
    public void Difficile(ActionEvent event)
    {
        this.reagir();
    }
    @FXML
    public void Correct(ActionEvent event)
    {
        this.reagir();
    }
    @FXML
    public void Revoir(ActionEvent event)
    {
        this.reagir();
    }
    @FXML
    public void Facile(ActionEvent event)
    {
        this.reagir();
    }
    @FXML
    public void exit()
    {
        this.deck.setCurrentView("DeckList");
    }
    public void reagir()
    {
        /*int leng = deck.getDeck().size();
        int i = new Random().nextInt(leng);
        card = deck.getDeck().get(i);
        Rep.setVisible(false);
        gridP.setVisible(false);
        Ques.setText(card.getQuestion());
        Rep.setText(card.getAnswer());
        repButton.setVisible(true);*/
    }
}