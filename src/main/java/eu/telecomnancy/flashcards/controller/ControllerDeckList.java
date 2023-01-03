package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Deck;

public class ControllerDeckList implements Observer {

    private Deck deck;

    public ControllerDeckList(Deck deck) {
        this.deck = deck;
        this.deck.ajouterObs(this);
    }

    public void switchToCardList() {
        this.deck.setCurrentView("CardList");
    }

    public void switchToNewCard() {
        this.deck.setCurrentView("NewCard");
    }

    @Override
    public void reagir() {

    }
}
