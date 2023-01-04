package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.sql.connect.UpdateApp;

public class Card {
    
    private String question;
    private String answer;
    
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Card() {
        this.question = null;
        this.answer = null;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
}
