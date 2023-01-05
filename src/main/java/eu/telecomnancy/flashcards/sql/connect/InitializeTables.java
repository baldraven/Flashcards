package eu.telecomnancy.flashcards.sql.connect;

import eu.telecomnancy.flashcards.sql.tables.Cards;
import eu.telecomnancy.flashcards.sql.tables.DateStats;
import eu.telecomnancy.flashcards.sql.tables.Decks;
import eu.telecomnancy.flashcards.sql.tables.Parameters;
import eu.telecomnancy.flashcards.sql.tables.RelationCardsDecks;

public class InitializeTables {
    public InitializeTables() {
        Cards.createNewTable();
        Decks.createNewTable();
        RelationCardsDecks.createNewTable();
        DateStats.createNewTable();
        Parameters.createNewTable();
    }
}
