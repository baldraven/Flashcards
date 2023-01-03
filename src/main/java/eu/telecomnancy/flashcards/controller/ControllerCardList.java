package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Deck;

public class ControllerCardList implements Observer {

    private Deck deck;

    public ControllerCardList(Deck deck) {
        this.deck = deck;
        this.deck.ajouterObs(this);
    }

    public void switchToDeckList() {
        this.deck.setCurrentView("DeckList");
    }

    @Override
    public void reagir() {

    }

}
