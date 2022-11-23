package pinkas.michael.sem.c.struktury.bvs;

import java.util.Iterator;

public interface IAbstrTable<K extends Comparable<K>, V> {

    /**
     * Metoda odebere všechny prvky ze stromu a seznam se stane prázdným.
     */
    void zrus();

    /**
     * Metoda zjistí zda se ve stromu nachází nějaké prkvy.
     *
     * @return true v případě, že seznam neobsahuje žádné prvky
     */
    boolean jePrazdny();

    /**
     * Metoda vrátí hodnotu hledaného klíče. V případě, že strom neobsahuje
     * hledaný klíč je navrácena hodnota null.
     *
     * @param key hledaný klíč
     * @return hodnota pro hledaný klíč
     * @throws AbstrTableException v případě, že hledaný klíč je null
     */
    V najdi(K key);

    /**
     * Metoda vloží vkládaný klíč do stromu na příslušnou pozici. V případě,
     * že klíč ve stromu již existuje, hodnota starého klíče není aktualizován.
     *
     * @param key   vkládaný klíč
     * @param value hodnota pro vkládaný klíč
     * @throws AbstrTableException v případě, že vkládaný klíč/hodnota klíče
     *                             jsou null.
     */
    void vloz(K key, V value) throws AbstrTableException;

    /**
     * Metoda odebere hledaný klíč a vrátí jeho hodnotu. V případě, že strom
     * neobsahuje hledaný klíč pro odebrání, nebo je prázdný je navrácena null.
     *
     * @param key hledaný klíč k odebrání
     * @return hodnota pro odebíraný klíč
     * @throws AbstrTableException v případě, že odebíraný klíč je null
     */
    V odeber(K key) throws AbstrTableException;

    /**
     * Metoda vrátí iterátor stromu na základě požadovaného typu průchodu
     * stromem.
     *
     * @param typ typ prohlídky stromu
     * @return iterátor
     */
    Iterator<V> vytvorIterator(eTypProhl typ);

}
