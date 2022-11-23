package pinkas.michael.sem.c.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import pinkas.michael.sem.c.data.Zamek;

import java.util.Optional;

public class ZamekDialog {

    private static final Dialog<Zamek> dialog = new Dialog<>();
    private static final GridPane gridPaneContent = new GridPane();
    private static final DialogPane dp = new DialogPane();
    private static final ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    private static final TextField tfNazev = new TextField();
    private static final TextField tfGPS = new TextField();

    private ZamekDialog() {
    }

    static {
        dp.setContent(gridPaneContent);
        dialog.setDialogPane(dp);

        gridPaneContent.setVgap(10);
        gridPaneContent.setHgap(10);
        gridPaneContent.setAlignment(Pos.CENTER);

        dp.getButtonTypes().addAll(okButton, new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));


        gridPaneContent.add(new Label("Název"), 0, 0);
        gridPaneContent.add(tfNazev, 1, 0);

        gridPaneContent.add(new Label("GPS"), 0, 1);
        gridPaneContent.add(tfGPS, 1, 1);
    }

    public static Optional<Zamek> zobrazDejDialog() {
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                return dejNovyZamek();
            }
            return null;
        });

        return dialog.showAndWait();
    }

    private static Zamek dejNovyZamek() {
        return new Zamek(tfNazev.getText(), tfGPS.getText());
    }
}
