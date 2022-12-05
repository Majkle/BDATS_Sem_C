package pinkas.michael.sem.c.pamatky;

import pinkas.michael.sem.c.data.Zamek;
import pinkas.michael.sem.c.struktury.bvs.AbstrTable;
import pinkas.michael.sem.c.struktury.bvs.AbstrTableException;
import pinkas.michael.sem.c.struktury.bvs.eTypProhl;
import pinkas.michael.sem.c.util.Soubor;
import pinkas.michael.sem.c.util.ZamekMapy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

public class Pamatky implements IPamatky {

    private final AbstrTable<String, Zamek> abstrTable;
    private eTypKey typKlice;

    public Pamatky() {
        this.abstrTable = new AbstrTable<>();
        typKlice = eTypKey.NAZEV;
    }

    @Override
    public int importDatZTXT(String soubor) {
        int exitCode = 0;
        try {
            Stream<Zamek> stream = Soubor.nacti(Path.of(soubor), ZamekMapy.txtRadekNaZamek());
            stream.forEachOrdered(this::_vlozZamek);
        } catch (IOException e) {
            exitCode = 1;
        }

        return exitCode;
    }

    @Override
    public int vlozZamek(Zamek zamek) {
        int exitCode = 0;

        try {
            _vlozZamek(zamek);
        } catch (AbstrTableException e) {
            exitCode = 1;
        }

        return exitCode;
    }

    @Override
    public Zamek najdiZamek(String klic) {
        return abstrTable.najdi(klic);
    }

    @Override
    public Zamek odeberZamek(String klic) {
        return abstrTable.odeber(klic);
    }

    @Override
    public void zrus() {
        abstrTable.zrus();
    }

    @Override
    public void prebuduj() {
        _prebuduj();
    }


    @Override
    public void nastavKlic(eTypKey typ) {
        typKlice = typ;
        _prebuduj();
    }

    @Override
    public Zamek najdiNejbliz(double latitude, double longtitude) throws StringIndexOutOfBoundsException {
        Zamek nejblizsi = null;

        double hledanyLat = latitude;
        double hledanyLon = longtitude;

        Iterator<Zamek> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        double prochazenyLat, prochazenyLon;
        double nejmensi = Double.MAX_VALUE;
        while (it.hasNext()) {
            Zamek prochazeny = it.next();

            prochazenyLat = Double.valueOf(prochazeny.getGps().substring(4, 11));
            prochazenyLon = Double.valueOf(prochazeny.getGps().substring(17, 24));

            double theta = hledanyLon - prochazenyLon;
            double dist = Math.sin(Math.toRadians(hledanyLat)) * Math.sin(Math.toRadians(prochazenyLat)) + Math.cos(Math.toRadians(hledanyLat)) * Math.cos(Math.toRadians(prochazenyLat)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            if (dist < nejmensi) {
                nejmensi = dist;
                nejblizsi = prochazeny;
            }

        }

        return nejblizsi;
    }

    @Override
    public Iterator<Zamek> vytvorIterator(eTypProhl typ) {
        return abstrTable.vytvorIterator(typ);
    }

    public void vypisStromu() {
        abstrTable.vypisStromu();
    }

    private void _vlozZamek(Zamek zamek) {
        switch (typKlice) {
            case NAZEV -> abstrTable.vloz(zamek.getNazevPamatky(), zamek);
            case GPS -> abstrTable.vloz(zamek.getGps(), zamek);
        }
    }

    private void _prebuduj() {
        ArrayList<Zamek> zamky = new ArrayList<>();

        //in-order zámky dle původního klíče
        abstrTable.vytvorIterator(eTypProhl.HLOUBKA).forEachRemaining(zamky::add);

        //Seřazení zámků dle typu klíče
        switch (typKlice) {
            case NAZEV -> zamky.sort(Comparator.comparing(Zamek::getNazevPamatky));
            case GPS -> zamky.sort(Comparator.comparing(Zamek::getGps));
        }

        abstrTable.zrus();

        prebudujRekurze(zamky, 0, zamky.size() - 1);
    }

    private void prebudujRekurze(ArrayList<Zamek> zamky, int min, int max) {
        if (min > max) {
            return;
        }

        int stred = (min + max) / 2;

        _vlozZamek(zamky.get(stred));

        prebudujRekurze(zamky, min, stred - 1);
        prebudujRekurze(zamky, stred + 1, max);
    }
}
