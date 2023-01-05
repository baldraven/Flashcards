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

    public ArrayList<String> getDeckNames() {
        ArrayList<String> deckNames = new ArrayList<>();
        for (Deck deck : this.deckList) {
            deckNames.add(deck.getName());
        }
        return deckNames;
    }

    public Deck searchDeckByName(String searchedName) {
        for (Deck deck : this.deckList) {
            if (deck.getName().equals(searchedName)) {
                return deck;
            }
        }
        return null;
    }

    public void deleteDeckByName(String name) {
        ArrayList<Deck> deckListCopy = new ArrayList<>();
        for (Deck deck : this.deckList) {
            if (!deck.getName().equals(name)) {
                //System.out.println("Deck conservé : " + deck.getName());
                deckListCopy.add(deck);
            }
        }
        this.deckList = deckListCopy;
    }
}
