package pinkas.michael.sem.c.ui.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pinkas.michael.sem.c.data.Zamek;
import pinkas.michael.sem.c.pamatky.eTypKey;
import pinkas.michael.sem.c.ui.Operace;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProgHeap extends Application {

    public static void main(String[] args) {
        launch();
    }


    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    private final Operace operace = new Operace(this::alertInfo, this::alertError);

    private final BorderPane root = new BorderPane();
    private final ListView<Zamek> listView = new ListView<>();

    private final FlowPane flowPaneHorniNavigace = new FlowPane();
    private final Button bVloz = new Button("Vlož");
    private final Button bOdeber = new Button("Odeber");
    private final Button bNajdi = new Button("Najdi");
    private final Button bNejblizsi = new Button("Nejbližší");
    private final TextField tfKlic = new TextField();
    private final TextField tfX = new TextField();
    private final TextField tfY = new TextField();

    private final Button bNastavKlic = new Button("Nastav");
    private final ComboBox<eTypKey> cbKlice = new ComboBox<>(FXCollections.observableArrayList(eTypKey.values()));

    private final Button bPrebuduj = new Button("Přebuduj");

    private final FlowPane flowPaneDolniNavigace = new FlowPane();
    private final Button bVypisStromu = new Button("Výpis stromu");

    private final Button bGenerator = new Button("Generuj");
    final Spinner<Integer> spinnerGenerujPocet = new Spinner<>(0, 100, 0, 1);
    private final Button bImport = new Button("Načti");
    private final Button bExport = new Button("Ulož");
    private final TextField tfSoubor = new TextField();
    private final Button bZrus = new Button("Zruš");

    /*------*/

    private final Button bVybuduj = new Button("Vybuduj");
    private final Button bPrebudujFrontu = new Button("Přebuduj");
    private final Button bZrusFrontu = new Button("Zruš");
    private final Button bVlozDoFronty = new Button("Vlož");
    private final Button bOdeberMax = new Button("Odeber max");
    private final Button bZpristupniMax = new Button("Zpřístupni max");
    private final Button bImportFronty = new Button("Načti");
    private final Button bExportFronty = new Button("Ulož");
    private final TextField tfSouborFronty = new TextField();


    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Výrobní proces");
        stage.show();

        root.setCenter(listView);
        root.setTop(flowPaneHorniNavigace);
        root.setBottom(flowPaneDolniNavigace);

        listView.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);

        flowPaneHorniNavigace.getChildren().addAll(new Label("AbstrTable:"), bVloz, bOdeber, bNajdi, tfKlic,
                new Label("|"), bNejblizsi, tfX, tfY,
                new Label("|"), bNastavKlic, cbKlice,
                new Label("|"), bPrebuduj,
                new Label("|"), bVypisStromu,
                new Label("|"), bGenerator, spinnerGenerujPocet,
                new Label("|"), bImport, bExport, tfSoubor,
                new Label("|"), bZrus);
        flowPaneHorniNavigace.setPadding(new Insets(10));
        flowPaneHorniNavigace.setHgap(10);
        flowPaneHorniNavigace.setVgap(10);
        flowPaneHorniNavigace.alignmentProperty().set(Pos.CENTER);

        flowPaneDolniNavigace.getChildren().addAll(new Label("AbstrHeap:"), bVybuduj, bPrebudujFrontu, bZrusFrontu, new Label("|"), bVlozDoFronty, bOdeberMax, bZpristupniMax, new Label("|"), bExportFronty, bImportFronty, tfSouborFronty);
        flowPaneDolniNavigace.setPadding(new Insets(10));
        flowPaneDolniNavigace.setHgap(10);
        flowPaneDolniNavigace.setVgap(10);
        flowPaneDolniNavigace.alignmentProperty().set(Pos.CENTER);

        bVloz.setOnAction(actionEvent -> {
            Optional<Zamek> novy = ZamekDialog.zobrazDejDialog();
            novy.ifPresent(operace::vlozZamek);
            aktualizujListView();
        });

        bOdeber.setOnAction(actionEvent -> {
            operace.odeberZamek(tfKlic.getText());
            aktualizujListView();
        });

        bNajdi.setOnAction(actionEvent -> {
            operace.najdiZamek(tfKlic.getText());
            aktualizujListView();
        });

        bNejblizsi.setOnAction(actionEvent -> {
            operace.najdiNejblizsi(Double.parseDouble(tfX.getText()), Double.parseDouble(tfY.getText()));
            aktualizujListView();
        });
//TODO obrázek 4 patra + hledání podle X Y
        tfKlic.setPromptText("klíč");

        tfX.setPromptText("x");
        tfX.setMaxWidth(50);

        tfY.setPromptText("y");
        tfY.setMaxWidth(50);

        bNastavKlic.setOnAction(actionEvent -> {
            operace.nastavKlic(cbKlice.getSelectionModel().getSelectedItem());
            aktualizujListView();
        });

        cbKlice.getSelectionModel().selectFirst();

        bPrebuduj.setOnAction(actionEvent -> {
            operace.prebuduj();
            aktualizujListView();
        });

        bVypisStromu.setOnAction(actionEvent -> operace.vypisStrom());

        bGenerator.setOnAction(actionEvent -> {
            operace.generuj(spinnerGenerujPocet.getValue());
            aktualizujListView();
        });

        spinnerGenerujPocet.setMaxWidth(80);

        bImport.setOnAction(actionEvent -> {
            operace.importDat(tfSoubor.getText());
            aktualizujListView();
        });

        bExport.setOnAction(actionEvent -> operace.exportDat(tfSoubor.getText()));

        bZrus.setOnAction(actionEvent -> {
            operace.zrus();
            aktualizujListView();
        });

        tfSoubor.setText("src/main/resources/vzorovy.txt");

        /*---*/

//        bVybuduj.setOnAction(actionEvent -> operace.);
    }


    private void aktualizujListView() {
        listView.getItems().clear();

        List<Zamek> list = new LinkedList<>();
        operace.iterator().forEachRemaining(list::add);

        listView.getItems().setAll(list);
    }

    private void alertInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operace proběhla v pořádku");
        alert.setHeaderText(msg);
        alert.show();
    }

    private void alertError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nastala chyba");
        alert.setHeaderText(msg);
        alert.show();
    }
}
