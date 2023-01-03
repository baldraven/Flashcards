package eu.telecomnancy.flashcards;

import java.util.ArrayList;

public abstract class Observable {

    private ArrayList<Observer> obs;

    public void ajouterObs(Observer o) {
        this.obs.add(o);
    }

    public void notifierObs() {
        for (Observer o : this.obs) {
            o.reagir();
        }
    }

}
