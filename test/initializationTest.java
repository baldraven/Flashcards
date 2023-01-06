package eu.telecomnancy.flashcards;

import eu.telecomnancy.flashcards.sql.connect.Reboot;
import org.junit.jupiter.api.Test;

import eu.telecomnancy.flashcards.model.*;
import eu.telecomnancy.flashcards.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class initializationTest {
    Reboot reboot = new Reboot(true);
    Initialization init = new Initialization(true);
    DeckList deckList = init.getDeckList();
    CardList cardList = init.getCardList();
    @Test
    void correctlyImported() {
        assertEquals(2, deckList.getDeckList().size());
        assertEquals(12, cardList.getCardList().size());
    }
}