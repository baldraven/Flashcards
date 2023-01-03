package eu.telecomnancy.flashcards.objects;

import java.util.ArrayList;

public class CardList {
    
    public ArrayList<Card> cardList;
    
    public CardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public CardList() {
        this.cardList = new ArrayList<Card>();
    }

    public void addCard(ArrayList<Card> cardList, Card card) {
        cardList.add(card);
    }
}
