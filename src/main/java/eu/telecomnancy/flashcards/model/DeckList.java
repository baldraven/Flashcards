package eu.telecomnancy.flashcards.model;

import java.util.ArrayList;

public class DeckList {
    
    public ArrayList<Deck> deckList;

    public DeckList(ArrayList<Deck> deckList) {
        this.deckList = deckList;
    }

    public DeckList() {
        this.deckList = new ArrayList<Deck>();
    }

    public void addDeck(Deck deck) {
        deckList.add(deck);
    }
}
