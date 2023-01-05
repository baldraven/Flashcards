package eu.telecomnancy.flashcards.sql.connect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import eu.telecomnancy.flashcards.model.DateStats;



public class StatsAdmin {
    
    public StatsAdmin() {}
    
    public void incrementStats(Integer created, Integer studied, Integer again, Integer hard, Integer good, Integer easy) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatter);

        if (todayStatsExists()) {
            SelectApp selectApp = new SelectApp();
            DateStats oldStats = selectApp.getDeckDateStats(date);
            
            UpdateApp updateApp = new UpdateApp();
            updateApp.updateDateStats(date, oldStats.getCreated() + created, oldStats.getStudied() + studied, oldStats.getAgain() + again, oldStats.getHard() + hard, oldStats.getGood() + good, oldStats.getEasy() + easy);
        } else {
            InsertApp insertApp = new InsertApp();
            insertApp.insertStats(date, created, studied, again, hard, good, easy);
        }
    }

    private boolean todayStatsExists() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatter);

        SelectApp app = new SelectApp();
        DateStats stats = app.getDeckDateStats(date);

        if (stats.getStudied() == null) {
            return false;
        } else {
            return true;
        }
    }
}
