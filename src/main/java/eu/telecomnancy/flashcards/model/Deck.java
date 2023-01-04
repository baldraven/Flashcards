package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.Observable;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;
import eu.telecomnancy.flashcards.sql.connect.UpdateApp;

import java.util.ArrayList;

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
        
        SelectApp selectApp = new SelectApp();
        InsertApp insertApp = new InsertApp();

        ArrayList<String> questionList = selectApp.selectAllQuestionsCards();

        if (!questionList.contains(card.getQuestion())) {
            insertApp.insertCard(card.getQuestion(), card.getAnswer());
        }
        
        insertApp.insertRelationCardsDecks(card.getQuestion(), this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateName(String name) {
        String oldName = this.name;
        String newName = name;
        this.name = newName;

        UpdateApp app = new UpdateApp();
        app.updateDeck(oldName, newName, this.description);
    }

    public void updateDescription(String description) {
        this.description = description;

        UpdateApp app = new UpdateApp();
        app.updateDeck(this.name, this.name, description);
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

