module eu.telecomnancy.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.controlsfx.controls;

    opens eu.telecomnancy.flashcards to javafx.fxml;
    exports eu.telecomnancy.flashcards;
}