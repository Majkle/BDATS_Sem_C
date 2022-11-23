package pinkas.michael.sem.c.struktury.heap;

import java.util.Iterator;

public interface IAbstrHeap<T extends Comparable<T>> {
    /**
     * Vybuduje levostrannou haldu z vloženého pole. Halda bude obsahovat největší prvek jako prioritní.
     *
     * @param data data pro vložení
     */
    void vybuduj(T[] data);

    /**
     * Přebuduje haldu. až na to, že to mám v plánu dělat během insertu, nebo nemám? non sense. ale ok. doable
     */
    void prebuduj();

    /**
     * Zruší obsah levostranné haldy.
     */
    void zrus();

    /**
     * Zjistí, zda je halda prázdná.
     *
     * @return true v případě, že halda nebosahuje žádné prvky.
     */
    boolean jePrazdny();

    /**
     * Vloží prvek na konec haldy.
     *
     * @param vkladany prvek
     */
    void vloz(T vkladany);

    /**
     * Odebere prvek s nejvyšší hodnout.
     *
     * @return odebraný prvek
     */
    T odeberMax();

    /**
     * Zpřístupní prvke s nejvyšší hodnoutou.
     *
     * @return nalezený prvek
     */
    T zpristupniMax();

    /**
     * Vrátí iterátor na základě {@link eTypProhl}
     *
     * @return iterátor
     */
    Iterator<T> iterator(eTypProhl typProhlidky);

}
