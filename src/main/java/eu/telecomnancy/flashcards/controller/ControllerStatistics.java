package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class ControllerStatistics extends AbstractControllerMenu implements Observer, Initializable {

    Integer again;
    Integer hard;
    Integer good;
    Integer easy;
    
    @FXML
    Label nbCards;
    @FXML
    Label nbDecks;
    @FXML
    PieChart repartition;
    @FXML
    PieChart results;
    
    public ControllerStatistics(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("Stats", this);
    }

    public void reload() {
        this.again = 0;
        this.hard = 0;
        this.good = 0;
        this.easy = 0;
        this.repartition.getData().clear();
        this.results.getData().clear();
        SelectApp selectApp = new SelectApp();
        nbCards.setText("Nombre de cartes: " + selectApp.selectCardCount());
        nbDecks.setText("Nombre de decks: " + selectApp.selectDeckCount());
        for (Deck deck : this.model.getDeckList().getDeckList()) {
            System.out.println(deck.getName());
            PieChart.Data slice = new PieChart.Data(deck.getName(), deck.getDeck().size());
            repartition.getData().add(slice);
            for (Card card : deck.getDeck()) {
                this.again += card.getAgain();
                this.hard += card.getHard();
                this.good += card.getGood();
                this.easy += card.getEasy();
            }
        }
        PieChart.Data slice1 = new PieChart.Data("A revoir", again);
        PieChart.Data slice2 = new PieChart.Data("Difficile", hard);
        PieChart.Data slice3 = new PieChart.Data("Correct", good);
        PieChart.Data slice4 = new PieChart.Data("Facile", easy);
        results.getData().add(slice1);
        results.getData().add(slice2);
        results.getData().add(slice3);
        results.getData().add(slice4);
    }

    @Override
    public void reagir() {
        reload();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}