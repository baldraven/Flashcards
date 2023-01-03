package eu.telecomnancy.flashcards.sql.connect;

import eu.telecomnancy.flashcards.sql.tables.Cards;
import eu.telecomnancy.flashcards.sql.tables.Decks;
import eu.telecomnancy.flashcards.sql.tables.RelationCardsDecks;
import eu.telecomnancy.flashcards.sql.tables.User;

public class InitializeTables {
    public static void main(String[] args) {
        Cards.createNewTable();
        Decks.createNewTable();
        RelationCardsDecks.createNewTable();
        User.createNewTable();
    }
}
