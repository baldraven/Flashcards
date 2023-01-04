package eu.telecomnancy.flashcards.controller;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.text.DecimalFormat;
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
    @FXML
    private Label tempRev;
    @FXML
    private Label tempEasy;
    @FXML
    private Label tempHard;
    @FXML
    private Label tempCor;
    private Deck deck;
    private ModelFlashcard model;
    private Card card;
    private long time;

    public ControllerLearning(ModelFlashcard model)
    {
        this.model = model;
        this.model.getViewChanger().ajouterObs(this);
    }

    public void AffRep(ActionEvent event)
    {
        Rep.setVisible(true);
        gridP.setVisible(true);
        repButton.setVisible(false);
        if(card.getInterval() == -1)
        {
            tempRev.setText("1 min");
            tempCor.setText("10 min");
            tempHard.setText("6 min");
            tempEasy.setText("4 jour");
        }
        else
        {
            DecimalFormat df = new DecimalFormat("0.0");
            tempRev.setText(df.format(card.getInterval()*0.5) + " min");
            tempCor.setText(df.format(card.getInterval() * card.getEase()) + " min");
            tempHard.setText(df.format(card.getInterval() * 1.2) + " min");
            tempEasy.setText(df.format(card.getInterval() * card.getEase()) + " min");
        }
    }

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

    public void Correct(ActionEvent event)
    {
        if (card.getInterval() == -1)
        {
            card.setInterval(10);
        }
        else
        {
            card.setInterval(card.getInterval() * card.getEase());;
        }
        this.reagirAction();
    }

    public void Revoir(ActionEvent event)
    {
        if (card.getInterval() == -1)
        {
            card.setInterval(1);
        }
        else
        {
            card.setInterval(card.getInterval() * 0.5);;
        }
        card.setEase(card.getEase()-20);
        this.reagirAction();
    }

    public void Facile(ActionEvent event)
    {
        if (card.getInterval() == -1)
        {
            card.setInterval(24*60*4);
        }
        else
        {
            card.setInterval(card.getInterval() * card.getEase());
        }
        this.reagirAction();
    }

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
            for(int j = 0;j < leng;j++)
            {
                time = deck.getCard(j).getTimer();
            }
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

    public void quit()
    {
        Platform.exit();
    }
}