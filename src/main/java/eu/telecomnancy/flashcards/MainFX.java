package eu.telecomnancy.flashcards;

import java.io.File;

import eu.telecomnancy.flashcards.controller.*;
import eu.telecomnancy.flashcards.model.ModelFlashcard;
import eu.telecomnancy.flashcards.sql.Initialization;
import eu.telecomnancy.flashcards.sql.connect.ExportApp;
import eu.telecomnancy.flashcards.sql.connect.Reboot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainFX extends Application {
    public static void main_(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TN's Flashcards");
        //primaryStage.setResizable(false);
        primaryStage.setWidth(800);
        primaryStage.setHeight(850);
        
        //Database
        File file = new File("database.db");
        Boolean reboot;
        if (file.exists()) {
            reboot = false;
        } else {
            reboot = true;
        }
        new Reboot(reboot);
        Initialization init = new Initialization(reboot);

        ViewChanger viewChanger = new ViewChanger();
        ModelFlashcard model = new ModelFlashcard(viewChanger, init.getDeckList(), init.getCardList(), init.getParam());

        FXMLLoader loaderDeckList = new FXMLLoader();
        loaderDeckList.setLocation(getClass().getResource("ViewDeckList.fxml"));
        loaderDeckList.setControllerFactory(iC->new ControllerDeckList(model));
        Parent rootDeckList = loaderDeckList.load();

        FXMLLoader loaderCardList = new FXMLLoader();
        loaderCardList.setLocation(getClass().getResource("ViewCardList.fxml"));
        loaderCardList.setControllerFactory(iC->new ControllerCardList(model));
        Parent rootCardList = loaderCardList.load();

        FXMLLoader loaderDeckContent = new FXMLLoader();
        loaderDeckContent.setLocation(getClass().getResource("ViewDeckContent.fxml"));
        loaderDeckContent.setControllerFactory(iC->new ControllerDeckContent(model));
        Parent rootDeckContent = loaderDeckContent.load();
 
        FXMLLoader loaderNewCard = new FXMLLoader();
        loaderNewCard.setLocation(getClass().getResource("ViewNewCard.fxml"));
        loaderNewCard.setControllerFactory(iC->new ControllerNewCard(model));
        Parent rootNewCard = loaderNewCard.load();

        FXMLLoader loaderNewDeck = new FXMLLoader();
        loaderNewDeck.setLocation(getClass().getResource("ViewNewDeck.fxml"));
        loaderNewDeck.setControllerFactory(iC->new ControllerNewDeck(model));
        Parent rootNewDeck = loaderNewDeck.load();

        FXMLLoader loaderLearning = new FXMLLoader();
        loaderLearning.setLocation(getClass().getResource("ViewLearning.fxml"));
        loaderLearning.setControllerFactory(iC->new ControllerLearning(model));
        Parent rootLearning = loaderLearning.load();

        FXMLLoader loaderModCard = new FXMLLoader();
        loaderModCard.setLocation(getClass().getResource("ViewModCard.fxml"));
        loaderModCard.setControllerFactory(iC->new ControllerModCard(model));
        Parent rootModCard = loaderModCard.load();

        FXMLLoader loaderParam = new FXMLLoader();
        loaderParam.setLocation(getClass().getResource("ViewParam.fxml"));
        loaderParam.setControllerFactory(iC->new ControllerParam(model));
        Parent rootParam = loaderParam.load();

        FXMLLoader loaderStats = new FXMLLoader();
        loaderStats.setLocation(getClass().getResource("ViewStatistics.fxml"));
        loaderStats.setControllerFactory(iC->new ControllerStatistics(model));
        Parent rootStats = loaderStats.load();

        viewChanger.addRoot("DeckList", rootDeckList);
        viewChanger.addRoot("CardList", rootCardList);
        viewChanger.addRoot("DeckContent", rootDeckContent);
        viewChanger.addRoot("NewCard", rootNewCard);
        viewChanger.addRoot("NewDeck", rootNewDeck);
        viewChanger.addRoot("Learning", rootLearning);
        viewChanger.addRoot("ModCard", rootModCard);
        viewChanger.addRoot("Param", rootParam);
        viewChanger.addRoot("Stats", rootStats);

        Scene scene = new Scene(rootDeckList);
        model.getViewChanger().setScene(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
