module pinkas.michael.sem.c {
    requires javafx.controls;
    requires java.logging;

    opens pinkas.michael.sem.c.ui to javafx.fxml;
    exports pinkas.michael.sem.c.ui;
}