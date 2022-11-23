package pinkas.michael.sem.c.struktury.adl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {

    private static class Prvek<E> {
        final E data;
        Prvek<E> predchudce;
        Prvek<E> naslednik;

        Prvek(E data) {
            this.data = data;
            this.predchudce = null;
            this.naslednik = null;
        }
    }

    private Prvek<T> prvni;
    private Prvek<T> aktualni;

    public AbstrDoubleList() {
        this.prvni = null;
        this.aktualni = null;
    }

    @Override
    public void zrus() {
        prvni = null;
        aktualni = null;
    }

    @Override
    public boolean jePrazdny() {
        return _jePrazdny();
    }

    private boolean _jePrazdny() {
        return prvni == null;
    }

    @Override
    public void vlozPrvni(T data) {
        kontrolaDat(data);

        final Prvek<T> vkladany = new Prvek<>(data);

        if (_jePrazdny()) {
            _vlozPrvni(vkladany);
        } else {
            vkladany.naslednik = prvni;
            vkladany.predchudce = prvni.predchudce;

            prvni.predchudce.naslednik = vkladany;
            prvni.predchudce = vkladany;

            prvni = vkladany;
        }
    }

    private void _vlozPrvni(Prvek<T> vkladany) {
        vkladany.naslednik = vkladany;
        vkladany.predchudce = vkladany;
        prvni = vkladany;
    }

    @Override
    public void vlozPosledni(T data) {
        kontrolaDat(data);

        final Prvek<T> vkladany = new Prvek<>(data);

        if (_jePrazdny()) {
            _vlozPrvni(vkladany);
        } else {
            vkladany.naslednik = prvni;
            vkladany.predchudce = prvni.predchudce;

            prvni.predchudce.naslednik = vkladany;
            prvni.predchudce = vkladany;
        }
    }

    @Override
    public void vlozNaslednika(T data) {
        kontrolaDat(data);
        kontrolaAktualniPrvek();

        final Prvek<T> vkladany = new Prvek<>(data);
        Prvek<T> predeslyNaslednik = aktualni.naslednik;

        vkladany.predchudce = aktualni;
        vkladany.naslednik = predeslyNaslednik;

        predeslyNaslednik.predchudce = vkladany;
        aktualni.naslednik = vkladany;
    }

    @Override
    public void vlozPredchudce(T data) {
        kontrolaDat(data);
        kontrolaAktualniPrvek();

        final Prvek<T> vkladany = new Prvek<>(data);
        Prvek<T> predeslyPredchudce = aktualni.predchudce;


        vkladany.naslednik = aktualni;
        vkladany.predchudce = predeslyPredchudce;

        aktualni.predchudce = vkladany;
        predeslyPredchudce.naslednik = vkladany;

        if (aktualni == prvni) {
            prvni = vkladany;
        }
    }

    @Override
    public T zpristupniAktualni() {
        kontrolaAktualniPrvek();

        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni() {
        kontrolaPlnostiListu();

        aktualni = prvni;

        return prvni.data;
    }

    @Override
    public T zpristupniPosledni() {
        kontrolaPlnostiListu();

        aktualni = prvni.predchudce;

        return aktualni.data;
    }

    @Override
    public T zpristupniNaslednika() {
        kontrolaAktualniPrvek();

        aktualni = aktualni.naslednik;

        return aktualni.data;
    }

    @Override
    public T zpristupniPredchudce() {
        kontrolaAktualniPrvek();

        aktualni = aktualni.predchudce;

        return aktualni.data;
    }

    @Override
    public T odeberAktualni() {
        kontrolaAktualniPrvek();

        Prvek<T> odebirany = aktualni;

        aktualni.predchudce.naslednik = aktualni.naslednik;
        aktualni.naslednik.predchudce = aktualni.predchudce;

        kontrolaOdebiranyPrvek(odebirany);

        return odebirany.data;
    }

    @Override
    public T odeberPrvni() {
        kontrolaPlnostiListu();

        Prvek<T> odebirany = prvni;

        prvni.predchudce.naslednik = prvni.naslednik;
        prvni.naslednik.predchudce = prvni.predchudce;

        kontrolaOdebiranyPrvek(odebirany);

        return odebirany.data;
    }

    @Override
    public T odeberPosledni() {
        kontrolaPlnostiListu();

        Prvek<T> odebirany = prvni.predchudce;

        prvni.predchudce.predchudce.naslednik = prvni;
        prvni.predchudce = prvni.predchudce.predchudce;

        aktualni = prvni.predchudce;

        kontrolaOdebiranyPrvek(odebirany);

        return odebirany.data;
    }

    @Override
    public T odeberNaslednika() {
        kontrolaAktualniPrvek();

        Prvek<T> odebirany = aktualni.naslednik;

        aktualni.naslednik.naslednik.predchudce = aktualni;
        aktualni.naslednik = aktualni.naslednik.naslednik;

        kontrolaOdebiranyPrvek(odebirany);

        return odebirany.data;
    }

    @Override
    public T odeberPredchudce() {
        kontrolaAktualniPrvek();

        Prvek<T> odebirany = aktualni.predchudce;

        aktualni.predchudce.predchudce.naslednik = aktualni;
        aktualni.predchudce = aktualni.predchudce.predchudce;

        kontrolaOdebiranyPrvek(odebirany);

        return odebirany.data;
    }

    @Override
    public T odeber(T data) {
        kontrolaPlnostiListu();
        kontrolaDat(data);

        Prvek<T> prochazeny = prvni;

        while (prochazeny.naslednik != prvni) {
            if (prochazeny.data == data) {
                prochazeny.predchudce.naslednik = prochazeny.naslednik;
                prochazeny.naslednik.predchudce = prochazeny.predchudce;

                break;
            }

            prochazeny = prochazeny.naslednik;
        }

        kontrolaOdebiranyPrvek(prochazeny);

        return prochazeny.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Prvek<T> prochazeny;

            @Override
            public boolean hasNext() {
                return !_jePrazdny() && prochazeny != prvni;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    if (prochazeny == null) {
                        prochazeny = prvni;
                    }
                    T data = prochazeny.data;
                    prochazeny = prochazeny.naslednik;
                    return data;
                } else {
                    throw new NoSuchElementException("List je prázdný");
                }
            }
        };
    }

    /**
     * Metoda vrátí stream listů.
     *
     * @return list převedený na stream.
     */
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * @throws AbstrDoubleListException v případě, že list neobsahuje žádné
     *                                  prvky.
     */
    private void kontrolaPlnostiListu() throws AbstrDoubleListException {
        if (_jePrazdny()) {
            throw new AbstrDoubleListException("List je prázdný");
        }
    }

    /**
     * @throws AbstrDoubleListException v případě, že list neobsahuje žádné
     *                                  prvky, nebo není nastaven aktuální
     *                                  prvek.
     */
    private void kontrolaAktualniPrvek() throws AbstrDoubleListException {
        kontrolaPlnostiListu();

        if (aktualni == null) {
            throw new AbstrDoubleListException("Není nastaven aktuální prvek");
        }
    }

    /**
     * @param data vkládaná data
     * @throws AbstrDoubleListException v případě, že vkládaná data jsou null.
     */
    private void kontrolaDat(T data) throws AbstrDoubleListException {
        if (data == null) {
            throw new AbstrDoubleListException("Žádná data");
        }
    }

    /**
     * Metoda provede kontrolu odebíraného prvku a případně nastaví hodnoty pro
     * {@link AbstrDoubleList#prvni} a {@link AbstrDoubleList#aktualni}.
     *
     * @param odebirany odebíraný prvek ze seznamu
     */
    private void kontrolaOdebiranyPrvek(Prvek<T> odebirany) {
        kontrolaOdebiranyJePrvniASeznamMa1Prvek(odebirany);
        kontrolaOdebiranyJePrvniASeznamMaVicePrvku(odebirany);
        kontrolaOdebiranyJeAktualni(odebirany);
    }

    /**
     * Metoda nastaví aktuální prvek na null v případě, že odebíraný je
     * zároveň aktuální.
     *
     * @param odebirany odebíraný prvek ze seznamu
     */
    private void kontrolaOdebiranyJeAktualni(Prvek<T> odebirany) {
        if (odebirany == aktualni) {
            aktualni = prvni;
        }
    }

    /**
     * Metoda nastaví první prvek v případě, že odebíraný prvek byl první a
     * seznam se stává prázdným.
     *
     * @param odebirany odebíraný prvek ze seznamu
     */
    private void kontrolaOdebiranyJePrvniASeznamMa1Prvek(Prvek<T> odebirany) {
        if (odebirany == prvni && prvni.naslednik == prvni) {
            prvni = null;
        }
    }

    /**
     * Metoda nastaví první prvek v případě, že odebíraný prvek byl první.
     *
     * @param odebirany odebíraný prvek ze seznamu
     */
    private void kontrolaOdebiranyJePrvniASeznamMaVicePrvku(Prvek<T> odebirany) {
        if (odebirany == prvni) {
            prvni = prvni.naslednik;
        }
    }
}
