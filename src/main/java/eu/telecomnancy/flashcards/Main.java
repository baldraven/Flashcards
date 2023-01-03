package eu.telecomnancy.flashcards;

import eu.telecomnancy.flashcards.controller.ControllerNewCard;
import eu.telecomnancy.flashcards.controller.ViewChanger;
import eu.telecomnancy.flashcards.model.Deck;
import eu.telecomnancy.flashcards.view.ViewNewCard;
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

        Deck deck = new Deck();

        FXMLLoader loaderNewCard = new FXMLLoader();
        loaderNewCard.setLocation(getClass().getResource("ViewNewCard.fxml"));
        loaderNewCard.setControllerFactory(iC->new ControllerNewCard(deck));
        Parent rootNewCard = loaderNewCard.load();

        Scene scene = new Scene(rootNewCard);

        // A modifier quand on modifie le constructeur de ViewChanger
        ViewChanger viewChanger = new ViewChanger(deck, primaryStage, scene, rootNewCard);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
