package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.model.ModelFlashcard;

public class ControllerCardList{


    private ModelFlashcard model;
    public ControllerCardList(ModelFlashcard model) {
        this.model = model;
    }

    public void switchToDeckList() {
        model.getViewChanger().setView("DeckList");
    }

    public void switchToNewCard() {
        model.getViewChanger().setView("NewCard");
    }
}
