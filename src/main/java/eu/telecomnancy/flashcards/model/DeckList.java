package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.Observable;

import java.util.ArrayList;

public class DeckList extends Observable {
    
    private ArrayList<Deck> deckList;

    public DeckList(ArrayList<Deck> deckList) {
        this.deckList = deckList;
    }

    public DeckList() {
        this.deckList = new ArrayList<Deck>();
    }

    public void addDeck(Deck deck) {
        deckList.add(deck);
    }

    public ArrayList<Deck> getDeckList() {
        return this.deckList;
    }

    public Deck searchDeckByName(String searchedName) {
        for (Deck deck : this.deckList) {
            if (deck.getName().equals(searchedName)) {
                return deck;
            }
        }
        return null;
    }
}
