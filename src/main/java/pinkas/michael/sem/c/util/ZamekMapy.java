package pinkas.michael.sem.c.util;

import pinkas.michael.sem.c.data.Zamek;

import java.util.function.Function;

public class ZamekMapy {

    private ZamekMapy() {
    }

    public static Function<Zamek, String> zamekNaTxtRadek() {
        return ZamekMapy::_zamekNaTxtRadek;
    }


    public static Function<String, Zamek> txtRadekNaZamek() {
        return ZamekMapy::_txtRadekNaZamek;
    }

    private static String _zamekNaTxtRadek(Zamek zamek) {
        return String.format("%-2s %-15s %24s %-24s %-20s^ %10s %12s",
                'W',
                "ZKRAKTA",
                zamek.getGps(),
                "DATUM",
                zamek.getNazevPamatky(),
                "18^Black",
                "^S+N^0"
        );
    }

    private static Zamek _txtRadekNaZamek(String txtRadek) {
        int gpsStart = 19;
        int gpsKonec = gpsStart + 25;
        int nazevStart = 69;
        int nazevKonec = nazevStart + 20;

        String gps = txtRadek.substring(gpsStart, gpsKonec).trim();
        String nazev = txtRadek.substring(nazevStart, nazevKonec).trim();


        return new Zamek(nazev, gps);
    }

}
