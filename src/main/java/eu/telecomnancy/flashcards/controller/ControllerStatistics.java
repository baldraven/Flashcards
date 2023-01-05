package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.DateStats;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class ControllerStatistics extends AbstractControllerMenu {

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
    @FXML
    CategoryAxis category;
    @FXML
    NumberAxis number;
    @FXML
    BarChart<String,Number> barChart;
    
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

        //Bar chart
        category.setLabel("Date");
        number.setLabel("Nombre de cartes");

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("Cartes étudiées");

        ArrayList<DateStats> statList = selectApp.getAllStats();
        for (DateStats stat : statList) {
            dataSeries1.getData().add(new XYChart.Data<String, Number>(stat.getDate(), stat.getStudied()));
        }

        barChart.getData().add(dataSeries1);
    }

    @Override
    public void reagir() {
        reload();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}