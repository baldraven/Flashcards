package eu.telecomnancy.flashcards.controller;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.flashcards.Observer;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.model.Param;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerParam extends AbstractControllerMenu implements Observer, Initializable
{
    @FXML
    private CheckBox oneSelec;
    @FXML
    private CheckBox isSec;
    @FXML
    private TextField sec;
    private Param param;

    public ControllerParam(ModelFlashcard model)
    {
        super(model);
        this.model.getViewChanger().ajouterObs("Param", this);
        this.reagir();
    }

    @Override
    public void reagir(){
        this.param = this.model.getParam();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        if(param.getOneTime())
        {
            oneSelec.setSelected(true);
        }
        if(param.getisSecond())
        {
            sec.setDisable(false);
            isSec.setSelected(true);
        }
        else
        {
            sec.setDisable(true);
        }
        sec.setText(String.valueOf(param.getsecond()));
    }

    @FXML
    public void changingEffect()
    {
        if(isSec.isSelected())
        {
            sec.setDisable(false);
        }
        else
        {
            sec.setDisable(true);
        }
    }

    @FXML
    public void applyChange()
    {
        try
        {
            int i = Integer.parseInt(sec.getText());
            param.setIsSecond(isSec.isSelected());
            param.setOneTime(oneSelec.isSelected());
            param.setSecond(i);
            param.updateParam();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Enregistrer !");
            alert.setContentText("Les paramètres ont été modifiés avec succès !");
            alert.showAndWait();
            this.model.getViewChanger().setView("DeckList");
        }
        catch(NumberFormatException nfe)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Le nombre de secondes indiqué est incorrect.");
            alert.showAndWait();
        }
    }

}