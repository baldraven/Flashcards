package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.controller.ViewChanger;

public class ModelFlashcard {
    private ViewChanger viewChanger;
    private DeckList deckList;
    private CardList cardList;
    private Deck selectedDeck;
    public ModelFlashcard(ViewChanger viewChanger, DeckList deckList, CardList cardList) {
        this.viewChanger = viewChanger;
        this.deckList = deckList;
        this.cardList = cardList;
    }
    public void setSelectedDeck(Deck deck) {
        this.selectedDeck = deck;
    }
    public Deck getSelectedDeck() {
        return this.selectedDeck;
    }
    public DeckList getDeckList() {
        return this.deckList;
    }
    public CardList getCardList() {
        return this.cardList;
    }
    public ViewChanger getViewChanger() {
        return this.viewChanger;
    }
}
