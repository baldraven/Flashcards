package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.Observable;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;

import java.util.ArrayList;
import java.util.List;

public class Deck extends Observable {
    
    private ArrayList<Card> deck;
    private String name;
    private String description;

    private String currentView = "DeckList";
    
    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        this.deck.add(card);

        InsertApp app = new InsertApp();
        app.insertCard(card.getQuestion(), card.getAnswer());
        app.insertRelationCardsDecks(card.getQuestion(), this.name);
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

    public String getCurrentView() {
        return this.currentView;
    }

    public void setCurrentView(String view) {
        this.currentView = view;
        this.notifierObs();
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public Card getCard(int i)
    {
        int leng = deck.size();
        if(i >= leng)
        {
            return null;
        }
        else
        {
            return deck.get(i);
        }
    }
}

