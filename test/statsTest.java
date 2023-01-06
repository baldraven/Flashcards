package eu.telecomnancy.flashcards;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.flashcards.model.*;
import eu.telecomnancy.flashcards.sql.*;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;
import eu.telecomnancy.flashcards.sql.connect.StatsAdmin;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class statsTest {
    Initialization init = new Initialization(true);
    DeckList deckList = init.getDeckList();
    CardList cardList = init.getCardList();
    @Test
    void correctlyIncremented() {
        //Initialisation de stats à la base de données
        StatsAdmin admin = new StatsAdmin();
        admin.incrementStats(0,0,0,0,0,0);

        SelectApp selectApp = new SelectApp();
        DateStats beforeDateStats = selectApp.getAllStats().get(selectApp.getAllStats().size()-1);

        //Sélection des anciennes stats
        Integer beforeStudied = beforeDateStats.getStudied();
        Integer beforeCreated = beforeDateStats.getCreated();
        Integer beforeAgain = beforeDateStats.getAgain();
        Integer beforeHard = beforeDateStats.getHard();
        Integer beforeGood = beforeDateStats.getGood();
        Integer beforeEasy = beforeDateStats.getEasy();

        //Ajouts de stats à la base de données
        admin.incrementStats(0,1,0,3,0,1);
        admin.incrementStats(4,0,2,0,4,1);

        //Sélection des nouvelles stats
        DateStats afterDateStats = selectApp.getAllStats().get(selectApp.getAllStats().size()-1);
        
        Integer afterStudied = afterDateStats.getStudied();
        Integer afterCreated = afterDateStats.getCreated();
        Integer atferAgain = afterDateStats.getAgain();
        Integer atferHard = afterDateStats.getHard();
        Integer atferGood = afterDateStats.getGood();
        Integer atferEasy = afterDateStats.getEasy();

        //Vérification
        assertEquals(beforeStudied + 1, afterStudied);
        assertEquals(beforeCreated + 4, afterCreated);
        assertEquals(beforeAgain + 2, atferAgain);
        assertEquals(beforeHard + 3, atferHard);
        assertEquals(beforeGood + 4, atferGood);
        assertEquals(beforeEasy + 2, atferEasy);
    }
}