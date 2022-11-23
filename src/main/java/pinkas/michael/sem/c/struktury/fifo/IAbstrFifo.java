package pinkas.michael.sem.c.struktury.fifo;

public interface IAbstrFifo<T> extends Iterable<T> {

    /**
     * Metoda odebere všechny prvky ze zásobníku a zásobník se stane prázdným.
     */
    void zrus();

    /**
     * Metoda zjistí zda se v zásobníku nachází nějaké prkvy.
     *
     * @return true v případě, že seznam neobsahuje žádné prvky
     */
    boolean jePrazdny();

    /**
     * Metoda vloží prvek na začátek zásobníku.
     *
     * @param data vkládaná data
     * @throws AbstrFifoException v případě, že vkládaná data jsou null
     */
    void vloz(T data) throws AbstrFifoException;

    /**
     * Metoda odebere prvek ze začátku zásobníku.
     *
     * @return odebíraný prvek
     * @throws AbstrFifoException v případě, že zásobník je prázdný
     */
    T odeber() throws AbstrFifoException;
}
