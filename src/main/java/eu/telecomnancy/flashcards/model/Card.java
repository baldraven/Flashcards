package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.sql.connect.UpdateApp;

public class Card {
    
    private String question;
    private String answer;
    private double ease;
    private double interval;
    
    public Card(String question, String answer, double ease, double interval) {
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

    public void setEase(double ease)
    {
        this.ease = ease;
    }

    public void setInterval(double interval)
    {
        this.interval = interval;
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

    public double getEase() {
        return this.ease;
    }

    public double getInterval()
    {
        return this.interval;
    }
}
