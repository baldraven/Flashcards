package eu.telecomnancy.flashcards;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.flashcards.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class deckListTest {
    DeckList deckList = new DeckList();

    @Test
    void deck() {
        Deck deck = new Deck();
        deck.setName("deck1");
        Deck deck2 = new Deck();
        deck2.setName("deck2");
        deckList.addDeck(deck);
        deckList.addDeck(deck2);
        assertEquals(deckList.getDeckNames().get(1), "deck2");
        assertEquals(deckList.searchDeckByName("deck1").getName(), "deck1");
    }
}
