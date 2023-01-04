package eu.telecomnancy.flashcards.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
public class ControllerLearning implements Observer, Initializable
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
    private ModelFlashcard model;
    private Card card;
    private int test;

    public ControllerLearning(ModelFlashcard model)
    {
        this.model = model;
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
        this.model.getViewChanger().setView("DeckList");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
     //   Ques.setText(String.valueOf(test));
     //   Rep.setText("wat");
    }

    public void reagir()
    {
        int leng = deck.getDeck().size();
        if (leng < 1)
        {
            this.model.getViewChanger().setView("DeckList");
        }
        else
        {
            Random rand = new Random();
            int i = rand.nextInt(0, leng);
            card = deck.getCard(i);
            Rep.setVisible(false);
            gridP.setVisible(false);
            Ques.setText(String.valueOf(test));
            Rep.setText(card.getAnswer());
            repButton.setVisible(true);
        }
    }
}