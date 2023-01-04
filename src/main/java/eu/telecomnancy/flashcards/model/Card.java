package eu.telecomnancy.flashcards.model;

import eu.telecomnancy.flashcards.sql.connect.DeleteApp;
import eu.telecomnancy.flashcards.sql.connect.UpdateApp;

public class Card {
    
    private String question;
    private String answer;
    private double ease;
    private long time;
    private double interval;
    private Integer again;
    private Integer hard;
    private Integer good;
    private Integer easy;
    
    public Card(String question, String answer, double ease, double interval, long time, Integer again, Integer hard, Integer good, Integer easy) {
        this.question = question;
        this.answer = answer;
        this.ease = ease;
        this.interval = interval;
        this.time = time;
        this.again = again;
        this.hard = hard;
        this.good = good;
        this.easy = easy;
    }

    public Card() {
        this.question = null;
        this.answer = null;
        this.ease = 230;
        this.interval = -1;
        this.time = 0;
        this.again = 0;
        this.hard = 0;
        this.good = 0;
        this.easy = 0;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTimer(long time) {
        this.time = time;
    }

    public void setEase(double ease) {
        this.ease = ease;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public void setAgain(Integer again) {
        this.again = again;
    }

    public void setHard(Integer hard) {
        this.hard = hard;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public void setEasy(Integer easy) {
        this.easy = easy;
    }

    public void updateQuestion(String question) {
        String oldQuestion = this.question;
        String newQuestion = question;
        this.question = newQuestion;

        UpdateApp app = new UpdateApp();
        app.updateCardQuestionAnswer(oldQuestion, newQuestion, this.answer);
    }

    public void updateAnswer(String answer) {
        this.answer = answer;

        UpdateApp app = new UpdateApp();
        app.updateCardQuestionAnswer(this.question, this.question, answer);
    }

    public void updateParameters(double ease, long time, double interval) {
        this.ease = ease;
        this.time = time;
        this.interval = interval;

        UpdateApp app = new UpdateApp();
        app.updateCardParameters(this.question, ease, time, interval, this.again, this.hard, this.good, this.easy);
    }

    public void updateStats(Integer again, Integer hard, Integer good, Integer easy) {
        this.again = again;
        this.hard = hard;
        this.good = good;
        this.easy = easy;

        UpdateApp app = new UpdateApp();
        app.updateCardParameters(this.question, this.ease, this.time, this.interval, again, hard, good, easy);
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public long getTimer() {
        return this.time;
    }

    public double getEase() {
        return this.ease;
    }

    public double getInterval() {
        return this.interval;
    }

    public Integer getAgain() {
        return this.again;
    }

    public Integer getHard() {
        return this.hard;
    }

    public Integer getGood() {
        return this.good;
    }

    public Integer getEasy() {
        return this.easy;
    }

    public void delete() {
        DeleteApp deleteApp = new DeleteApp();
        deleteApp.deleteCard(this.question);
    }
}
