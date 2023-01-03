package eu.telecomnancy.flashcards.controller;

import javafx.fxml.FXMLLoader;
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
    public Button repButton;
    @FXML
    public Label Rep;
    @FXML
    public GridPane gridP;
    @FXML
    public Label Ques;
    private Deck deck;
    private Card card;

    public void ControllerLearning(Deck deck)
    {
        this.deck = deck;
        this.reagir();
    }
    @FXML
    public void AffRep()
    {
        Rep.setVisible(true);
        gridP.setVisible(true);
    }
    @FXML
    public void Difficile()
    {
        this.reagir();
    }
    @FXML
    public void Correct()
    {
        this.reagir();
    }
    @FXML
    public void Revoir()
    {
        this.reagir();
    }
    @FXML
    public void Facile()
    {
        this.reagir();
    }
    public void reagir()
    {
        int leng = deck.deck.size();
        int i = new Random().nextInt(leng);
        card = deck.deck.get(i);
        Rep.setVisible(false);
        gridP.setVisible(false);
        Ques.setText(card.question);
        Rep.setText(card.answer);
    }
}