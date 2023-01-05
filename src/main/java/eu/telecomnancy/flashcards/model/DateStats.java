package eu.telecomnancy.flashcards.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateStats {
    
    private String date;
    private Integer created;
    private Integer studied;
    private Integer again;
    private Integer hard;
    private Integer good;
    private Integer easy;
    
    public DateStats() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = now.format(formatter);
        
        this.created = null;
        this.studied = null;
        this.again = null;
        this.hard = null;
        this.good = null;
        this.easy = null;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public void setStudied(Integer studied) {
        this.studied = studied;
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

    public Integer getCreated() {
        return this.created;
    }

    public Integer getStudied() {
        return this.studied;
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

    public String getDate() {
        return this.date;
    }
}
