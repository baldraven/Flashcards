package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import javafx.application.Platform;
import javafx.fxml.FXML;

public abstract class AbstractControllerMenu {

    protected ModelFlashcard model;

    public AbstractControllerMenu(ModelFlashcard model) {
        this.model = model;
    }

    @FXML
    public void quit()
    {
        Platform.exit();
    }

    public void switchToNewCard() {
        model.getViewChanger().setView("NewCard");
    }

    public void switchToNewDeck() {
        model.getViewChanger().setView("NewDeck");
    }

    public void switchToDeckList() {
        model.getViewChanger().setView("DeckList");
    }

    public void switchToCardList() {
        model.getViewChanger().setView("CardList");
    }

    public void switchToDeckContent(Deck deck) {
        model.setSelectedDeck(deck);
        model.getViewChanger().setView("DeckContent");
    }

    public void switchToLearning(Deck deck) {
        model.setSelectedDeck(deck);
        model.getViewChanger().setView("Learning");
    }

}
