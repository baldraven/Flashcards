package eu.telecomnancy.flashcards.sql.connect;

public class Reboot {
    
    public Reboot() {
        new DeleteDatabase();
        new Create();
        new InitializeTables();
    }
}
