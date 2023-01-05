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
    public void leave()
    {
        this.model.getViewChanger().setView("DeckList");
    }

    @FXML
    public void applyChange()
    {
        try
        {
            int i = Integer.parseInt(sec.getText());
            if(i < 3 || i > 60)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Plage de valeur incorrect.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else
            {
                param.setIsSecond(isSec.isSelected());
                param.setOneTime(oneSelec.isSelected());
                param.setSecond(i);
                param.updateParam();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Enregistrer !");
                alert.setContentText("Les paramètres ont été modifié avec succès !");
                alert.setHeaderText(null);
                alert.showAndWait();
                this.model.getViewChanger().setView("DeckList");
            }
        }
        catch(NumberFormatException nfe)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Le nombre de seconde indiqué est incorrecte.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void quit()
    {
        Platform.exit();
    }
}