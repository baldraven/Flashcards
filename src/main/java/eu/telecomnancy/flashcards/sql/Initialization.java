package eu.telecomnancy.flashcards.sql;

import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.DeckList;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;

public class Initialization {
    
    public void Initialize() {
        SelectApp app = new SelectApp();
        DeckList deckList = new DeckList();

        deckList = app.selectAllDecks(deckList);

        for (Deck deck : deckList.deckList) {
            deck = app.getCardsWithDeckName(deck, deck.getName());
        }
    }
}