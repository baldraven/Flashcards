package eu.telecomnancy.flashcards.sql;

import eu.telecomnancy.flashcards.model.Card;
import eu.telecomnancy.flashcards.model.CardList;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.DeckList;
import eu.telecomnancy.flashcards.sql.connect.InsertApp;
import eu.telecomnancy.flashcards.sql.connect.SelectApp;

public class Initialization {

    public DeckList deckList = new DeckList();
    public CardList cardList = new CardList();

    public Initialization() {
        this.InitializeDatabase();
        this.InitializeModels();
    }
    
    public void InitializeDatabase() {
        InsertApp app = new InsertApp();
        app.insertCard("Quel est la capitale du monde ?", "Limoges");
        app.insertCard("Arc-en-ciel en anglais", "Rainbow");
        app.insertCard("1+1", "2");
        app.insertCard("2+1", "3");
        app.insertCard("3+1", "4");
        app.insertCard("4+1", "5");
        app.insertCard("5+1", "6");
        app.insertCard("6+1", "7");

        app.insertDeck("Math", "Additions faciles");
        app.insertDeck("Culture", "Questions culturelles");

        app.insertUser("Jo", 0);

        app.insertRelationCardsDecks("Quel est la capitale du monde ?", "Culture");
        app.insertRelationCardsDecks("Arc-en-ciel en anglais", "Culture");
        app.insertRelationCardsDecks("1+1", "Math");
        app.insertRelationCardsDecks("2+1", "Math");
        app.insertRelationCardsDecks("3+1", "Math");
        app.insertRelationCardsDecks("4+1", "Math");
        app.insertRelationCardsDecks("5+1", "Math");
        app.insertRelationCardsDecks("6+1", "Math");
        
    }
    
    public void InitializeModels() {
        SelectApp app = new SelectApp();

        this.deckList = app.selectAllDecks(this.deckList);

        for (Deck deck : this.deckList.deckList) {
            deck = app.getCardsWithDeckName(deck, deck.getName());
            deck = app.getDescriptionWithDeckName(deck, deck.getName());
            for (Card card : deck.deck) {
                this.cardList.addCard(card);
                card = app.getAnswerWithCardQuestion(card, card.getQuestion());
            }
        }
    }
}
