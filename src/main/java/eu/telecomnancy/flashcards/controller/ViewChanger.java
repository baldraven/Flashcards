package eu.telecomnancy.flashcards.controller;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.Deck;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewChanger implements Observer
{
    private Deck deck;

    private Stage stage;

    private Scene scene;

    private Parent rootDeckList;

    private Parent rootCardList;

    private Parent rootDeckContent;

    private Parent rootLearning;

    private Parent rootNewCard;

    private Parent rootStatistics;

    public ViewChanger(Deck deck, Stage stage, Scene scene, Parent rootNewCard) {
        this.deck = deck;
        this.stage = stage;
        this.scene = scene;
        this.rootNewCard = rootNewCard;
        this.deck.ajouterObs(this);
    }

    public void setViewDeckList() {
        this.scene.setRoot(this.rootDeckList);
    }

    public void setViewCardList() {
        this.scene.setRoot(this.rootCardList);
    }

    public void setViewDeckContent() {
        this.scene.setRoot(this.rootDeckContent);
    }

    public void setViewLearning() {
        this.scene.setRoot(this.rootLearning);
    }

    public void setViewNewCard() {
        this.scene.setRoot(this.rootNewCard);
    }

    public void setViewStatistics() {
        this.scene.setRoot(this.rootStatistics);
    }

    @Override
    public void reagir() {
        switch (this.deck.getCurrentView()) {
            case "DeckList":
                this.setViewDeckList();
                break;
            case "CardList":
                this.setViewCardList();
                break;
            case "DeckContent":
                this.setViewDeckContent();
                break;
            case "Learning":
                this.setViewLearning();
                break;
            case "NewCard":
                this.setViewNewCard();
                break;
            case "Statistics":
                this.setViewStatistics();
                break;
            default:
                break;
        }
    }
}