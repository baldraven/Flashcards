module eu.telecomnancy.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.controlsfx.controls;
    requires java.sql;

    opens eu.telecomnancy.flashcards to javafx.fxml, java.sql;

    exports eu.telecomnancy.flashcards;
    exports eu.telecomnancy.flashcards.controller;
    opens eu.telecomnancy.flashcards.controller to java.sql, javafx.fxml;
}