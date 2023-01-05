package eu.telecomnancy.flashcards;

import java.util.HashMap;

public abstract class Observable {

    private HashMap<String, Observer> obs = new HashMap<String, Observer>();

    public void ajouterObs(String name, Observer o) {
        this.obs.put(name, o);
    }

    public void notifierObs(String name) {
        obs.get(name).reagir();
    }
}
