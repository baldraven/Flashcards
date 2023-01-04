package eu.telecomnancy.flashcards.controller;

import javafx.fxml.Initializable;
import javafx.print.PrintColor;
import javafx.application.Platform;
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

    public ControllerLearning(ModelFlashcard model)
    {
        this.model = model;
        this.model.getViewChanger().ajouterObs(this);
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
        if (card.getInterval() == -1)
        {
            card.setInterval(6);
        }
        else
        {
            card.setInterval(card.getInterval() * 1.2);;
        }
        card.setEase(card.getEase()-15);
        this.reagirAction();
    }
    @FXML
    public void Correct(ActionEvent event)
    {
        this.reagirAction();
    }
    @FXML
    public void Revoir(ActionEvent event)
    {
        this.reagirAction();
    }
    @FXML
    public void Facile(ActionEvent event)
    {
        this.reagirAction();
    }
    @FXML
    public void leave()
    {
        this.model.getViewChanger().setView("DeckList");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        /* if (this.deck != null)
        {
            Ques.setText(deck.getName());
            Rep.setText(deck.getDescription());
        } */
    }

    public void reagirAction()
    {
        int leng = deck.getDeck().size();
        if (leng < 1 || deck == null)
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
            Ques.setText(card.getQuestion());
            Rep.setText(card.getAnswer());
            repButton.setVisible(true);
        }
    }

    public void reagir(){
        this.deck = this.model.getSelectedDeck();
        this.reagirAction();
    }

    @FXML
    public void quit()
    {
        Platform.exit();
    }
}