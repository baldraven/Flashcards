package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.Observable;

import java.util.ArrayList;

public class DeckList extends Observable {
    
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

    public ArrayList<Deck> getDeckList(ArrayList<Deck> deckList) {
        return this.deckList;
    }
}
