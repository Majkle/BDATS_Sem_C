package pinkas.michael.sem.c.pamatky;

import pinkas.michael.sem.c.data.Zamek;
import pinkas.michael.sem.c.struktury.bvs.eTypProhl;

import java.util.Iterator;

public interface IPamatky {

    /**
     * Metoda provede import dat a načte je na konec seznamu.
     *
     * @param soubor cesta k souboru
     * @return exit code
     */
    int importDatZTXT(String soubor);

    /**
     * Vloží {@link Zamek} do tabulky. Pro vložení je použit typ klíče
     * nastavený v {@link IPamatky#nastavKlic(eTypKey)}. V případě, že klíč v
     * tabulce již existuje, zámek není vložen, ani není aktualizována
     * hodnota původního klíče.
     *
     * @param zamek vkládaný {@link Zamek}
     * @return exit code
     */
    int vlozZamek(Zamek zamek);

    /**
     * Nalezne a vrátí {@link Zamek} na základě typ klíče nastaveném v
     * {@link #nastavKlic(eTypKey)}. V případě, že tabulka neobsahuje klíč je
     * navrácena hodnota null.
     *
     * @param klic hledaný klíč
     * @return {@link Zamek} odpovídající hodnotě klíče
     */
    Zamek najdiZamek(String klic);

    /**
     * Odebere {@link Zamek} z tabulky na základě typ klíče nastaveném v
     * {@link #nastavKlic(eTypKey)}. V případě, že tabulka neobsahuje klíč je
     * navrácena hodnota null.
     *
     * @param klic klíč odebíraného {@link Zamek}
     * @return odebraný {@link Zamek}
     */
    Zamek odeberZamek(String klic);

    /**
     * Zruší obsah tabulky
     */
    void zrus();

    /**
     * Přebuduje tabulku pro optimalozivané operace na tabulce. V případě, že
     * se změnil typ klíče v {@link #nastavKlic(eTypKey)} je tabulka
     * přebudována s novým typem klíče. V případě, že po změně typu klíče
     * existuje více {@link Zamek} se stejným klíčem, tabukla obashuje pouze
     * jeden záznam a ostatní jsou zahozeny.
     */
    void prebuduj();

    /**
     * Nastaví typ klíče pro operace na tabulce. Při každé změně klíče je
     * tabulka přebudována.
     *
     * @param typ pro operace na tabulce
     */
    void nastavKlic(eTypKey typ);

    /**
     * Metoda vrátí {@link Zamek}, který má nejbližší polohu k hledánemu
     * klíčí. Nejbližší poloha se určuje na základě {@link Zamek#getGps()}.
     * V případě, že je nastaven typ klíče na {@link eTypKey#NAZEV} je
     * navrácena hodnota null.
     *
     * @param x Latitude
     * @return {@link Zamek} s nejblizší polohou ke hledanému klíči
     */
    Zamek najdiNejbliz(double latitude, double longtitude);

    /**
     * Vrátí iterátor pro tabulku na základě {@link eTypProhl}.
     *
     * @param typ prohlídky
     * @return iterátor
     */
    Iterator<Zamek> vytvorIterator(eTypProhl typ);

    /**
     * Provede výpis stromu do konzole
     */
    void vypisStromu();
}
