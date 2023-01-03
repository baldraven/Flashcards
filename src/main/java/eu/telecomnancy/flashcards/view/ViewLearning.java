package eu.telecomnancy.flashcards.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.controller.ControllerLearning;

public class ViewLearning implements Observer
{
    @FXML
    public Button repButton;

    private ControllerLearning Controler;

    public void ViewLearning()
    {
        Controler = new ControllerLearning();
        this.reagir();
    } 
    @FXML
    public void AffRep()
    {
        Controler.AfficherRep();
        this.reagir();
    }
    public void reagir()
    {

    }
}