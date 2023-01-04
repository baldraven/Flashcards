package eu.telecomnancy.flashcards.sql.connect;

import eu.telecomnancy.flashcards.sql.tables.Cards;
import eu.telecomnancy.flashcards.sql.tables.CardsStats;
import eu.telecomnancy.flashcards.sql.tables.DateStats;
import eu.telecomnancy.flashcards.sql.tables.Decks;
import eu.telecomnancy.flashcards.sql.tables.DecksStats;
import eu.telecomnancy.flashcards.sql.tables.RelationCardsDecks;
import eu.telecomnancy.flashcards.sql.tables.User;

public class InitializeTables {
    public InitializeTables() {
        Cards.createNewTable();
        Decks.createNewTable();
        RelationCardsDecks.createNewTable();
        User.createNewTable();
        CardsStats.createNewTable();
        DateStats.createNewTable();
        DecksStats.createNewTable();
    }
}
