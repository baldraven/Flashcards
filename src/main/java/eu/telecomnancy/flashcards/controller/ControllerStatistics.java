package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.model.ModelFlashcard;

public class ControllerStatistics extends AbstractControllerMenu {

    public ControllerStatistics(ModelFlashcard model) {
        super(model);
        this.model.getViewChanger().ajouterObs("Stats", this);
        this.reagir();
    }

    @Override
    public void reagir() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}