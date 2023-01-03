package eu.telecomnancy.flashcards.objects;

import java.util.ArrayList;

public class Deck {
    
    public ArrayList<Card> deck;
    public String name;
    public String description;

    
    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void addCard(ArrayList<Card> deck, Card card) {
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
}
