package pinkas.michael.sem.c.struktury.heap;

import pinkas.michael.sem.c.struktury.lifo.AbstrLifo;

import java.util.*;

public class AbstrHeap<T> implements IAbstrHeap<T> {

    private T[] prioritniFronta;
    private Comparator<T> komparator;

    public AbstrHeap(Comparator<T> komparator) {
        this.komparator = komparator;
    }

    @Override
    public void vybuduj(T[] zamky) {
        if (zamky == null) {
            throw new AbstrHeapException("Žádná data pro vložení");
        }

        prioritniFronta = zamky;
        _prebuduj();
    }

    @Override
    public void prebuduj() {
        if (!_jePrazdny()) {
            _prebuduj();
        }
    }

    @Override
    public void zrus() {
        prioritniFronta = null;
    }

    @Override
    public boolean jePrazdny() {
        return _jePrazdny();
    }

    @Override
    public void vloz(T vkladany) {
        if (vkladany == null) {
            throw new AbstrHeapException("Žádná data pro vložení");
        }

        if (_jePrazdny()) {
            prioritniFronta = (T[]) new Object[]{vkladany};
        } else {
            prioritniFronta = Arrays.copyOf(prioritniFronta, prioritniFronta.length + 1);

            int iZarazeni = 0;
            while (komparator.compare(prioritniFronta[iZarazeni], vkladany) > 0) {
                iZarazeni++;
                if (iZarazeni == prioritniFronta.length - 1) {
                    break;
                }
            }

            T prevValue = vkladany;

            for (int i = iZarazeni; i < prioritniFronta.length; i++) {
                T tmp = prevValue;
                prevValue = prioritniFronta[i];
                prioritniFronta[i] = tmp;
            }
        }
    }

    @Override
    public T odeberMax() {
        if (_jePrazdny()) {
            return null;
        }

        T odebirany = prioritniFronta[prioritniFronta.length - 1];
        prioritniFronta = Arrays.copyOf(prioritniFronta, prioritniFronta.length - 1);
        return odebirany;
    }

    @Override
    public T zpristupniMax() {
        if (_jePrazdny()) {
            return null;
        }

        return prioritniFronta[prioritniFronta.length - 1];
    }

    @Override
    public Iterator<T> iterator(eTypProhl typProhlidky) {
        if (_jePrazdny()) {
            return null;
        }

        Iterator<T> it;

        switch (typProhlidky) {
            case SIRKA -> it = Arrays.stream(prioritniFronta).iterator();
            case HLOUBKA -> it = new Iterator<T>() {
                AbstrLifo<Integer> zasobnik = new AbstrLifo<>();

                {
                    if (!_jePrazdny()) {
                        int pozice = 0;
                        while (pozice <= prioritniFronta.length - 1) {
                            zasobnik.vloz(pozice);
                            pozice = pozice * 2 + 1;
                        }
                    }
                }

                @Override
                public boolean hasNext() {
                    return !zasobnik.jePrazdny();
                }

                @Override
                public T next() {
                    if (hasNext()) {
                        int dalsi = zasobnik.odeber();

                        int pozice = dalsi * 2 + 2;
                        if (pozice < prioritniFronta.length) {
                            while (pozice < prioritniFronta.length) {
                                zasobnik.vloz(pozice);
                                pozice = pozice * 2 + 1;
                            }
                        }

                        return prioritniFronta[dalsi];
                    } else {
                        throw new NoSuchElementException("Žádný další klíč");
                    }
                }
            };
            default -> throw new AbstrHeapException("Nepodporovaný druh iterátoru");
        }

        return it;
    }

    private boolean _jePrazdny() {
        return prioritniFronta == null || prioritniFronta.length == 0;
    }

    private void _prebuduj() {
        int velikostPole = prioritniFronta.length;
        int polovinaVelikostiPole = velikostPole / 2;

        for (int aktualniKoren = polovinaVelikostiPole - 1; aktualniKoren >= 0; aktualniKoren--) {
            heapify(prioritniFronta, velikostPole, aktualniKoren);
        }

        for (int aktualniPosledniPrvek = velikostPole - 1; aktualniPosledniPrvek >= 0; aktualniPosledniPrvek--) {
            T tmp = prioritniFronta[0];
            prioritniFronta[0] = prioritniFronta[aktualniPosledniPrvek];
            prioritniFronta[aktualniPosledniPrvek] = tmp;

            heapify(prioritniFronta, aktualniPosledniPrvek, 0);
        }
    }

    private void heapify(T[] pole, int velikostPole, int aktualniKoren) {
        int iNejmensi = aktualniKoren;
        int iLevy = 2 * aktualniKoren + 1;
        int iPravy = 2 * aktualniKoren + 2;

        if (iLevy < velikostPole && komparator.compare(pole[iLevy], pole[iNejmensi]) < 0) {
            iNejmensi = iLevy;
        }

        if (iPravy < velikostPole && komparator.compare(pole[iPravy], pole[iNejmensi]) < 0) {
            iNejmensi = iPravy;
        }

        if (iNejmensi != aktualniKoren) {
            T tmp = pole[aktualniKoren];
            pole[aktualniKoren] = pole[iNejmensi];
            pole[iNejmensi] = tmp;

            heapify(pole, velikostPole, iNejmensi);
        }
    }
}
