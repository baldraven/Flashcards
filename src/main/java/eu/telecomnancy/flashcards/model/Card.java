package eu.telecomnancy.flashcards.model;

public class Card {
    
    public String question;
    public String answer;
    
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

    public void setCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }
}