package eu.telecomnancy.flashcards.objects;

import java.util.ArrayList;

public class DeckList {
    
    public ArrayList<Deck> deckList;

    public DeckList(ArrayList<Deck> deckList) {
        this.deckList = deckList;
    }

    public DeckList() {
        this.deckList = new ArrayList<Deck>();
    }

    public void addDeck(ArrayList<Deck> deckList, Deck deck) {
        deckList.add(deck);
    }
}
