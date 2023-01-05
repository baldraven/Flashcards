package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.controller.ViewChanger;

public class ModelFlashcard {
    private ViewChanger viewChanger;
    private DeckList deckList;
    private CardList cardList;
    private Deck selectedDeck;
    private Card selectedCard;
    private Param param;
    public ModelFlashcard(ViewChanger viewChanger, DeckList deckList, CardList cardList, Param param) {
        this.viewChanger = viewChanger;
        this.deckList = deckList;
        this.cardList = cardList;
        this.param = param;
    }
    public void setSelectedCard(Card card) {
        this.selectedCard = card;
    }
    public Card getSelectedCard() {
        return this.selectedCard;
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
    public Param getParam()
    {
        return this.param;
    }
    public void setParam(Param param)
    {
        this.param = param;
    }

    public void setDeckList(DeckList deckList) {
        this.deckList = deckList;
    }
}
