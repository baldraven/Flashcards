package eu.telecomnancy.flashcards.model;

import java.util.ArrayList;

public class CardList {
    
    private ArrayList<Card> cardList;
    
    public CardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public CardList() {
        this.cardList = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public ArrayList<Card> getCardList() {
        return this.cardList;
    }

    public ArrayList<String> getQuestions() {
        ArrayList<String> questionsList = new ArrayList<>();
        for (Card card : this.cardList) {
            questionsList.add(card.getQuestion());
        }
        return questionsList;
    }

    public void deleteCardByQuestion(String question) {
        ArrayList<Card> cardListCopy = new ArrayList<>();
        for (Card card : this.cardList) {
            if (!card.getQuestion().equals(question)) {
                cardListCopy.add(card);
            }
        }
        this.cardList = cardListCopy;
    }
}
