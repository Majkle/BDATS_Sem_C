package pinkas.michael.sem.c.ui;

import pinkas.michael.sem.c.data.Zamek;
import pinkas.michael.sem.c.struktury.bvs.eTypProhl;
import pinkas.michael.sem.c.util.Soubor;
import pinkas.michael.sem.c.util.ZamekGenerator;
import pinkas.michael.sem.c.util.ZamekMapy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public class Operace {
    private final Consumer<String> msgInfo;
    private final Consumer<String> msgError;

    public Operace(Consumer<String> msgInfo, Consumer<String> msgError) {
        this.msgInfo = msgInfo;
        this.msgError = msgError;
    }

    private void info(String msg) {
        if (msgInfo != null) {
            msgInfo.accept(msg);
        } else {
            Logger.getLogger(Operace.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    private void error(String msg) {
        if (msgError != null) {
            msgError.accept(msg);
        } else {
            Logger.getLogger(Operace.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    public void importDat(String soubor) {

    }

    public void exportDat(String soubor) {

    }

    public void vlozZamek(Zamek zamek) {

    }

    public void najdiZamek(String klic) {

    }

    public void odeberZamek(String klic) {

    }

    public void prebuduj() {

        info("Strom byl přebudován");
    }

    public void najdiNejblizsi(double x, double y) {
        try {
        } catch (Exception e) {
            error("Nastala chyba při hledání nejbližší památky");
        }
    }

    public void generuj(int pocet) {

        info("Procesy byly generovány");
    }

    public void zrus() {
    }

    public void vypisStrom() {
    }

    public Iterator<Zamek> iterator() {
        return null;
    }
}
