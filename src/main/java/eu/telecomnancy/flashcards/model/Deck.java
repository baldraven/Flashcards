package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.Observable;
import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;
import eu.telecomnancy.flashcards.sql.connect.UpdateApp;

import java.util.ArrayList;

public class Deck extends Observable {
    
    private ArrayList<Card> deck;
    private String name;
    private String description;
    private Integer again;
    private Integer hard;
    private Integer good;
    private Integer easy;

    private String currentView = "DeckList";

    public Deck(String name, String description, Integer again, Integer hard, Integer good, Integer easy) {
        this.deck = new ArrayList<Card>();
        this.name = name;
        this.description = description;
        this.again = again;
        this.hard = hard;
        this.good = good;
        this.easy = easy;
    }
    
    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        this.deck.add(card);
        
        SelectApp selectApp = new SelectApp();
        InsertApp insertApp = new InsertApp();

        ArrayList<String> questionList = selectApp.selectAllQuestionsCards();

        if (!questionList.contains(card.getQuestion())) {
            insertApp.insertCard(card.getQuestion(), card.getAnswer(), card.getInterval(), card.getEase(), card.getTimer(), card.getAgain(), card.getHard(), card.getGood(), card.getEasy());
        }
        
        insertApp.insertRelationCardsDecks(card.getQuestion(), this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAgain(Integer again) {
        this.again = again;
    }

    public void setHard(Integer hard) {
        this.hard = hard;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public void setEasy(Integer easy) {
        this.easy = easy;
    }

    public void updateName(String name) {
        String oldName = this.name;
        String newName = name;
        this.name = newName;

        UpdateApp app = new UpdateApp();
        app.updateDeckNameDescription(oldName, newName, this.description);
    }

    public void updateDescription(String description) {
        this.description = description;

        UpdateApp app = new UpdateApp();
        app.updateDeckNameDescription(this.name, this.name, description);
    }

    public void updateStats(Integer again, Integer hard, Integer good, Integer easy) {
        this.again = again;
        this.hard = hard;
        this.good = good;
        this.easy = easy;

        UpdateApp app = new UpdateApp();
        app.updateDeckParameters(this.name, again, hard, good, easy);
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

    public Integer getAgain() {
        return this.again;
    }

    public Integer getHard() {
        return this.hard;
    }

    public Integer getGood() {
        return this.good;
    }

    public Integer getEasy() {
        return this.easy;
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

    public ArrayList<String> getQuestionsFromDeck() {
        ArrayList<String> questionsListFromDeck = new ArrayList<>();
        for (Card card : this.deck) {
            questionsListFromDeck.add(card.getQuestion());
        }
        return questionsListFromDeck;
    }

    public void removeCardByQuestion(String question) { // La carte n'est pas supprimée, seulement retirée d'un deck
        ArrayList<Card> deckCopy = new ArrayList<>();
        for (Card card : this.deck) {
            if (!card.getQuestion().equals(question)) {
                deckCopy.add(card);
            }
        }
        this.deck = deckCopy;
    }

    public void delete() {
        DeleteApp deleteApp = new DeleteApp();
        deleteApp.deleteDeck(this.name);
    }

    public void deleteCardFromDeck(String question) {
        DeleteApp deleteApp = new DeleteApp();
        deleteApp.deleteCardFromDeck(question, this.name);
    }

    public boolean isQuestionInDeck(String question) {
        for (Card card : this.deck) {
            if (card.getQuestion().equals(question)) {
                return true;
            }
        }
        return false;
    }
}

