package eu.telecomnancy.flashcards.controller;

import java.util.HashMap;
import java.util.Map;

import eu.telecomnancy.flashcards.Observable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewChanger extends Observable
{
    private Scene scene;
    private Map<String, Parent> roots = new HashMap<String, Parent>();

    public void  setScene(Scene scene) {
        this.scene = scene;
    }

    public void addRoot(String name, Parent root) {
        this.roots.put(name, root);
    }

    public void setView(String name) {
        this.scene.setRoot(this.roots.get(name));
        notifierObs(name);  //Possibilite de modifier pour specifier un observateur 
    }
}