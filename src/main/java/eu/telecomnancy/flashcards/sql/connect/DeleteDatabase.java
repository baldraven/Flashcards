package eu.telecomnancy.flashcards.sql.connect;

import java.io.File;

public class DeleteDatabase {
    
    public DeleteDatabase() {
        this.delete();
    }

    /**
     * Delete database
     */
    public void delete() {
        File file = new File("database.db");

        if (file.delete()) {
            System.out.println("Database deleted successfully");
        }
        else {
            System.out.println("Failed to delete the database");
        }
    }
}
