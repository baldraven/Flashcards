package eu.telecomnancy.flashcards.sql.connect;

public class Reboot {
    
    public Reboot(Boolean reboot) {
        if (reboot) {
            new DeleteDatabase();
            new Create();
        }
        new InitializeTables();
    }
}
