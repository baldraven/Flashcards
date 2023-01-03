package eu.telecomnancy.flashcards.view;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.controller.ControllerNewCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class ViewNewCard implements Observer {

    private ControllerNewCard controllerNewCard;



    public ViewNewCard(ControllerNewCard controllerNewCard) {
        this.controllerNewCard = controllerNewCard;
    }



    public void addNewCardInManyDecks() {

    }

    @Override
    public void reagir() {

    }
}
