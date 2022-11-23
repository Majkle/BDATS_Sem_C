package pinkas.michael.sem.c.struktury.adl;

public interface IAbstrDoubleList<T> extends Iterable<T> {
    /**
     * Metoda odebere všechny prvky ze seznamu a seznam se stane prázdným.
     */
    void zrus();

    /**
     * Metoda zjistí zda se v seznamu nachází nějaké prkvy.
     *
     * @return true v případě, že seznam neobsahuje žádné prvky
     */
    boolean jePrazdny();

    /**
     * Metoda vloží prvek do seznamu na první pozici. V případě, že seznam již
     * obsahoval první prvek, stává se vkládaný prvek prvním a jeho
     * následníkem se stává předcházející první prvek seznamu.
     *
     * @param data vkládaná data
     * @throws AbstrDoubleListException v případě, že vkládaná data
     *                                  jsou null.
     */
    void vlozPrvni(T data) throws AbstrDoubleListException;

    /**
     * Metoda vloží prvek do seznamu na poslední pozici. V případě, že seznam
     * již obsahoval poslední prvek, stává se vkládaný prvek posledním a
     * původní poslední prvek seznamu se stává jeho předchůdcem.
     *
     * @param data vkládaná data
     * @throws AbstrDoubleListException v případě, že vkládaná data
     *                                  jsou null.
     */
    void vlozPosledni(T data) throws AbstrDoubleListException;

    /**
     * Metoda vloží prvek do seznamu za aktuální prvek. Vkládaný prvek se
     * stává následníkem aktuálního prvku a předešlý následník se stává
     * následníkem vkládaného prvku. V případě, že aktuální prvek je zároveň
     * prvek poslední, stává se z vkládaného prvku prvek poslední.
     *
     * @param data vkládaná data
     * @throws AbstrDoubleListException v případě, že vkládaná data
     *                                  jsou null, nebo není nastaven
     *                                  aktuální prvek.
     */
    void vlozNaslednika(T data) throws AbstrDoubleListException;

    /**
     * Metoda vloží prvek do seznamu před aktuální prvek. Vkládaný prvek se
     * stává předchůdcem aktuálního prvku a předešlý předchůdce se stává
     * předchůdcem vkládaného prvku. V případě, že aktuální prvek je zároveň
     * prvek první, stává se z vkládaného prvku prvek první.
     *
     * @param data vkládané data
     * @throws AbstrDoubleListException v případě, že vkládaná data
     *                                  jsou null, nebo není nastaven
     *                                  aktuální prvek.
     */


    void vlozPredchudce(T data) throws AbstrDoubleListException;

    /**
     * Zpřístupní prvek seznamu označený jako aktuální prvek.
     *
     * @return prvek, který je nastavený jako aktuální.
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T zpristupniAktualni() throws AbstrDoubleListException;

    /**
     * Zpřístupní první prvek v seznamu a nastaví první prvek seznamu jako
     * aktuální.
     *
     * @return první prvek v seznamu.
     * @throws AbstrDoubleListException v případě, že seznamu neobsahuje
     *                                  žádné prkvy
     */
    T zpristupniPrvni() throws AbstrDoubleListException;

    /**
     * Zpřístupní poslední prvek v seznamu a nastaví poslední prvek seznamu jako
     * aktuální.
     *
     * @return poslední prvek v seznamu.
     * @throws AbstrDoubleListException v případě, že seznamu neobsahuje
     *                                  žádné prkvy
     */
    T zpristupniPosledni() throws AbstrDoubleListException;

    /**
     * Zpřístupní prvek, který je následníkem prvku, který je označený jako
     * aktuální. V případě, že aktuální prvek je zároveň poslední je vrácen
     * první prvek seznamu. Zároveň je nastaven následník aktuálního prvku
     * jako aktuální.
     *
     * @return následník aktuálního prkvu
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T zpristupniNaslednika() throws AbstrDoubleListException;

    /**
     * Zpřístupní prvek, který je předchůdcem prvku, který je označený jako
     * aktuální. V případě, že aktuální prvek je zároveň první je vrácen
     * poslední prvek seznamu. Zároveň je nastaven následník aktuálního prvku
     * jako aktuální.
     *
     * @return předchůdce aktuálního prkvu
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T zpristupniPredchudce() throws AbstrDoubleListException;

    /**
     * Odebere prvek ze seznamu, který je nastaven jako aktuální. Následně je
     * jako aktuální prvek označen první prvek seznamu.
     *
     * @return odebíraný prvek
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T odeberAktualni() throws AbstrDoubleListException;

    /**
     * Odebere první prvek seznamu. Prvním prvek se stává následník
     * odebíraného prvku. V případě, že odebíraný prvek je zároveň prvek
     * aktuální, aktuálním prvkem se stává nový první prvek seznamu.
     *
     * @return odebíraný prvek
     * @throws AbstrDoubleListException v případě, že seznam neobsahuje žádné
     *                                  prkvy.
     */
    T odeberPrvni() throws AbstrDoubleListException;

    /**
     * Odebere poslední prvek seznamu. V případě, že poslední prvek je
     * nastaven zároveň jako aktuální, aktuálním se stává první prvek seznamu.
     *
     * @return odebíraný prvek
     * @throws AbstrDoubleListException v případě, že seznam neobsahuje žádné
     *                                  prkvy.
     */
    T odeberPosledni() throws AbstrDoubleListException;

    /**
     * Odebere následníka aktuálního prvku v seznamu. V případě, že je
     * následník zároveň poslední prvek, stává se posledním prvkem prvek
     * aktuální. V případě, že je následník zároveň první prvek, stává se
     * prvním prvkem následník odebíraného prvku.
     *
     * @return odebíraný prvek
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T odeberNaslednika() throws AbstrDoubleListException;

    /**
     * Odebere předchůdce aktuálního prvku. V případě, že je předchůdce
     * zároveň poslední prvek, stává se posledním prvkem předchůdce
     * odebíraného prvku. V případě, že je předchůdce zároveň první prvek,
     * stává se prvním prvkem prvek aktuální.
     *
     * @return odebíraný prvek
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T odeberPredchudce() throws AbstrDoubleListException;

    /**
     * Odebere prvek ze seznamu, který má stejný hash jako {@code data}.
     * V případě, že odebíraný prvek je zároveň aktuální prvek, aktuálním
     * prvkem seznamu se stává prvek první.
     *
     * @return odebíraný prvek
     * @throws AbstrDoubleListException v případě, že není nastaven aktuální
     *                                  prvek seznamu.
     */
    T odeber(T data) throws AbstrDoubleListException;
}
