package eu.telecomnancy.flashcards;

import eu.telecomnancy.flashcards.controller.*;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.sql.Initialization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TN's Flashcards");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        Initialization init = new Initialization();

        Deck deck = new Deck();

        FXMLLoader loaderDeckList = new FXMLLoader();
        loaderDeckList.setLocation(getClass().getResource("ViewDeckList.fxml"));
        loaderDeckList.setControllerFactory(iC->new ControllerDeckList(init.getDeckList()));
        Parent rootDeckList = loaderDeckList.load();

        FXMLLoader loaderCardList = new FXMLLoader();
        loaderCardList.setLocation(getClass().getResource("ViewCardList.fxml"));
        loaderCardList.setControllerFactory(iC->new ControllerCardList(deck));
        Parent rootCardList = loaderCardList.load();

        FXMLLoader loaderNewCard = new FXMLLoader();
        loaderNewCard.setLocation(getClass().getResource("ViewNewCard.fxml"));
        loaderNewCard.setControllerFactory(iC->new ControllerNewCard(deck));
        Parent rootNewCard = loaderNewCard.load();

        /*FXMLLoader loaderLearning = new FXMLLoader();
        loaderLearning.setLocation(getClass().getResource("ViewLearning.fxml"));
        loaderLearning.setControllerFactory(iC->new ControllerLearning(deck));
        Parent rootLearning = loaderLearning.load();*/

        Scene scene = new Scene(rootNewCard);

        // A modifier quand on modifie le constructeur de ViewChanger
        ViewChanger viewChanger = new ViewChanger(deck, primaryStage, scene, rootDeckList, rootCardList, rootNewCard);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
