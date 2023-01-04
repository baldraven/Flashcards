package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.sql.connect.UpdateApp;

public class Card {
    
    private String question;
    private String answer;
    private float ease;
    private int interval;
    
    public Card(String question, String answer, float ease, int interval) {
        this.question = question;
        this.answer = answer;
        this.ease = ease;
        this.interval = interval;
    }

    public Card() {
        this.question = null;
        this.answer = null;
        this.ease = 230;
        this.interval = -1;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setEase(float ease)
    {
        this.ease = ease;
    }

    public void updateQuestion(String question) {
        String oldQuestion = this.question;
        String newQuestion = question;
        this.question = newQuestion;

        UpdateApp app = new UpdateApp();
        app.updateCard(oldQuestion, newQuestion, this.answer);
    }

    public void updateAnswer(String answer) {
        this.answer = answer;

        UpdateApp app = new UpdateApp();
        app.updateCard(this.question, this.question, answer);
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public float getEase() {
        return this.ease;
    }

    public int getInterval()
    {
        return this.interval;
    }
}
