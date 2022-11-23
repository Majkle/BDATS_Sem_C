package pinkas.michael.sem.c.struktury.lifo;

public interface IAbstrLifo<T> extends Iterable<T>{

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
     * @throws AbstrLifoException v případě, že vkládaná data jsou null
     */
    void vloz(T data) throws AbstrLifoException;

    /**
     * Metoda odebere prvek ze začátku zásobníku.
     *
     * @return odebíraný prvek
     * @throws AbstrLifoException v případě, že zásobník je prázdný
     */
    T odeber() throws AbstrLifoException;
}
