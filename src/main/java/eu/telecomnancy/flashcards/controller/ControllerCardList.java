package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.ModelFlashcard;

public class ControllerCardList implements Observer{


    private ModelFlashcard model;
    public ControllerCardList(ModelFlashcard model) {
        this.model = model;
        this.model.getViewChanger().ajouterObs(this);
    }

    public void switchToDeckList() {
        model.getViewChanger().setView("DeckList");
    }

    public void switchToNewCard() {
        model.getViewChanger().setView("NewCard");
    }

    public void reagir(){}
}
