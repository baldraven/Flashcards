module eu.telecomnancy.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive org.controlsfx.controls;
    requires java.sql;

    opens eu.telecomnancy.flashcards to javafx.fxml, java.sql, org.testfx, org.junit.jupiter.api;

    exports eu.telecomnancy.flashcards;
    exports eu.telecomnancy.flashcards.controller;
    exports eu.telecomnancy.flashcards.model;
    opens eu.telecomnancy.flashcards.controller to java.sql, javafx.fxml;
}