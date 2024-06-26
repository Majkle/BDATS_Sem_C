package pinkas.michael.sem.c.ui;

import pinkas.michael.sem.c.data.Zamek;
import pinkas.michael.sem.c.pamatky.IPamatky;
import pinkas.michael.sem.c.pamatky.Pamatky;
import pinkas.michael.sem.c.pamatky.eTypKey;
import pinkas.michael.sem.c.struktury.bvs.eTypProhl;
import pinkas.michael.sem.c.struktury.heap.AbstrHeap;
import pinkas.michael.sem.c.struktury.heap.AbstrHeapException;
import pinkas.michael.sem.c.struktury.heap.IAbstrHeap;
import pinkas.michael.sem.c.util.Komparatory;
import pinkas.michael.sem.c.util.Soubor;
import pinkas.michael.sem.c.util.ZamekGenerator;
import pinkas.michael.sem.c.util.ZamekMapy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public class Operace {
    private final Consumer<String> msgInfo;
    private final Consumer<String> msgError;
    private final IPamatky pamatky;
    private final IAbstrHeap<Zamek> priorotniFronta;


    public Operace(Consumer<String> msgInfo, Consumer<String> msgError) {
        this.msgInfo = msgInfo;
        this.msgError = msgError;

        this.pamatky = new Pamatky();
        this.priorotniFronta = new AbstrHeap<>(Komparatory.komparatorGPS);
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
        if (pamatky.importDatZTXT(soubor) == 0) {
            info("Data byla načtena");
        } else {
            error("Nastala chyba při načítání dat");
        }
    }

    public void exportDat(String soubor) {
        try {
            Soubor.uloz(Path.of(soubor),
                    StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                                    pamatky.vytvorIterator(eTypProhl.HLOUBKA), Spliterator.ORDERED),
                            false),
                    ZamekMapy.zamekNaTxtRadek());

            info("Data byla uložena");
        } catch (IOException e) {
            error("Nastala chyba při načítání dat");
        }
    }

    public void vlozZamek(Zamek zamek) {
        if (pamatky.vlozZamek(zamek) == 1) {
            error("Nastala chyba při vkládání zámku do stromu");
        }
    }

    public void najdiZamek(String klic) {
        Zamek nalezeny = pamatky.najdiZamek(klic);
        if (nalezeny != null) {
            info("Nalezený: " + nalezeny);
        }
    }

    public void odeberZamek(String klic) {
        Zamek odebirany = pamatky.odeberZamek(klic);
        if (odebirany != null) {
            info("Odebraný: " + odebirany);
        }
    }

    public void prebuduj() {
        pamatky.prebuduj();

        info("Strom byl přebudován");
    }

    public void nastavKlic(eTypKey typ) {
        pamatky.nastavKlic(typ);

        info("Typ klíče byl nastaven");
    }

    public void najdiNejblizsi(double x, double y) {
        try {
            info("Nalezený: " + pamatky.najdiNejbliz(x, y).toString());
        } catch (Exception e) {
            error("Nastala chyba při hledání nejbližší památky");
        }
    }

    public void generuj(int pocet) {
        ZamekGenerator.generujZamky(pocet).forEachOrdered(pamatky::vlozZamek);

        info("Procesy byly generovány");
    }

    public void zrus() {
        pamatky.zrus();
    }

    public void vypisStrom() {
        pamatky.vypisStromu();
    }

    public Iterator<Zamek> iterator() {
        return pamatky.vytvorIterator(eTypProhl.HLOUBKA);
    }

    /*-----*/

    public void vybuduj() {
        Zamek[] zamky = StreamSupport.stream(Spliterators.spliteratorUnknownSize(pamatky.vytvorIterator(eTypProhl.SIRKA), Spliterator.ORDERED), false).toArray(Zamek[]::new);

        priorotniFronta.vybuduj(zamky);
    }

    public void prebudujFrontu() {
        priorotniFronta.prebuduj();
    }

    public void zrusFrontu() {
        priorotniFronta.zrus();
    }

    public void vlozDoFronty(Zamek zamek) {
        try {
            priorotniFronta.vloz(zamek);
            info("Zámek byl vložen do fronty");
        } catch (AbstrHeapException e) {
            error(e.getMessage());
        }
    }

    public void odeberMax() {
        Zamek max = priorotniFronta.odeberMax();
        if (max == null) {
            info("Prioritní fronta je prázdná");
        } else {
            info("Odebraný zámek: " + max);
        }
    }

    public void zpristupniMax() {
        Zamek max = priorotniFronta.zpristupniMax();
        if (max == null) {
            info("Prioritní fronta je prázdná");
        } else {
            info("Zpřístupněný zámek: " + max);
        }
    }

    public void importDatPrioritniFronta(String soubor) {
        try {
            Soubor.nacti(Path.of(soubor), ZamekMapy.txtRadekNaZamek()).forEachOrdered(priorotniFronta::vloz);
            info("Data byla načtena");
        } catch (IOException e) {
            error("Nastala chyba při načítání dat");
        }
    }

    public void exportDatPrioritniFronta(String soubor) {
        try {
            Soubor.uloz(Path.of(soubor),
                    StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                                    priorotniFronta.iterator(pinkas.michael.sem.c.struktury.heap.eTypProhl.SIRKA), Spliterator.ORDERED),
                            false),
                    ZamekMapy.zamekNaTxtRadek());

            info("Data byla uložena");
        } catch (IOException e) {
            error("Nastala chyba při ukládání dat");
        }
    }

    public Iterator<Zamek> iteratorPrioritniFronta() {
        return priorotniFronta.iterator(pinkas.michael.sem.c.struktury.heap.eTypProhl.SIRKA);
    }
}
