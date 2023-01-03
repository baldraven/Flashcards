package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.Observable;

import java.util.ArrayList;

public class Deck extends Observable {
    
    private ArrayList<Card> deck;
    private String name;
    private String description;

    
    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }
}
