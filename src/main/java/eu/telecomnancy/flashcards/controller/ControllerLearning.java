package eu.telecomnancy.flashcards.controller;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;

public class ControllerLearning extends AbstractControllerMenu implements Observer, Initializable
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
    private Card card;
    private long time;
    private ArrayList<Card> doneCards;

    public ControllerLearning(ModelFlashcard model)
    {
        super(model);
        this.model.getViewChanger().ajouterObs("Learning", this);
        doneCards = new ArrayList<Card>();
    }

    public void AffRep(ActionEvent event)
    {
        Rep.setVisible(true);
        gridP.setVisible(true);
        repButton.setVisible(false);
        if(card.getInterval() == -1)
        {
          /*   tempRev.setText("1 min");
            tempCor.setText("10 min");
            tempHard.setText("6 min");
            tempEasy.setText("4 jour"); */
        }
        else
        {
           /*  DecimalFormat df = new DecimalFormat("0.0");
            tempRev.setText(df.format(card.getInterval()*0.5) + " min");
            tempCor.setText(df.format(card.getInterval() * card.getEase()) + " min");
            tempHard.setText(df.format(card.getInterval() * 1.2) + " min");
            tempEasy.setText(df.format(card.getInterval() * card.getEase()) + " min"); */
        }
    }

    @FXML
    public void Difficile()
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
        card.setHard(card.getHard() + 1);
        card.updateParameters();
        this.reagirAction(); 
    } 
    
    @FXML
    public void Correct()
    {
        if (card.getInterval() == -1)
        {
            card.setInterval(10);
        }
        else
        {
            card.setInterval(card.getInterval() * card.getEase());;
        }
        card.setGood(card.getGood() + 1);
        card.updateParameters();
        this.reagirAction();
    }

    @FXML
    public void Revoir()
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
        card.setAgain(card.getAgain() + 1);
        card.updateParameters();
        this.reagirAction();
    }

    @FXML
    public void Facile()
    {
        if (card.getInterval() == -1)
        {
            card.setInterval(24*60*4);
        }
        else
        {
            card.setInterval(card.getInterval() * card.getEase());
        }
        card.setEasy(card.getEasy() + 1);
        card.updateParameters();
        this.reagirAction();
    }

    @FXML
    public void leave()
    {
        this.model.getViewChanger().setView("DeckList");
    }

    @FXML
    public void accesParam()
    {
        this.model.getViewChanger().setView("Param");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        /* if (this.deck != null)
        {
            Ques.setText(deck.getName());
            Rep.setText(deck.getDescription());
        } */
        tempHard.setVisible(false);
        tempEasy.setVisible(false);
        tempCor.setVisible(false);
        tempRev.setVisible(false);
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
            if(leng == doneCards.size())
            {
                doneCards.clear();
            }
            long unixtime = System.currentTimeMillis() / (1000L*60L);
            double max = Double.NEGATIVE_INFINITY;
            int check = 0;
            for(int j = 0;j < leng;j++)
            {
                if(doneCards.contains(deck.getCard(j)))
                {
                    continue;
                }
                time = deck.getCard(j).getTimer();
                if(deck.getCard(j).getInterval() >= 24L*60L)
                {
                    if(deck.getCard(j).getInterval() <= unixtime - deck.getCard(j).getTimer())
                    {
                        deck.getCard(j).setInterval(-1);
                    }
                    continue;
                }
                if(max < (unixtime - time - deck.getCard(j).getInterval()))
                {
                    max = unixtime - time - deck.getCard(j).getInterval();
                    card = deck.getCard(j);
                    check++;
                }
            }
            if(check == 0)
            {
               /* Stage stage = new Stage();
                stage.setTitle("Félicitation !");
                Popup popup = new Popup();
                Label label = new Label("Bravo ! Vous avez réussi toute les cartes de la pile "+ deck.getName() + " !");
                label.setMinWidth(150);
                label.setMinHeight(50);
                Button button = new Button("Retourner au menu");
                button.setOnAction(event -> this.model.getViewChanger().setView("DeckList"));
                popup.getContent().addAll(label, button);
                stage.show();*/
                this.model.getViewChanger().setView("DeckList");
            }
            else
            {
                card.setTimer(unixtime);
                doneCards.add(card);
            }
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