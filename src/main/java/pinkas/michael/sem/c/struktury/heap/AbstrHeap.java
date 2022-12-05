package pinkas.michael.sem.c.struktury.heap;

import pinkas.michael.sem.c.struktury.lifo.AbstrLifo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        // Index of last non-leaf node
        int polovinaVelikostiPole = velikostPole / 2;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int aktualniKoren = polovinaVelikostiPole - 1; aktualniKoren >= 0; aktualniKoren--) {
            heapify(prioritniFronta, velikostPole, aktualniKoren);
        }

        // One by one extract an element from heap
        for (int aktualniKoren = velikostPole - 1; aktualniKoren >= 0; aktualniKoren--) {
            // Move current root to end
            T tmp = prioritniFronta[0];
            prioritniFronta[0] = prioritniFronta[aktualniKoren];
            prioritniFronta[aktualniKoren] = tmp;

            // call max heapify on the reduced heap
            heapify(prioritniFronta, aktualniKoren, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private void heapify(T[] pole, int velikostPole, int aktualniKoren) {
        int iNejmensi = aktualniKoren; // Initialize largest as root
        int iLevy = 2 * aktualniKoren + 1; // left = 2*i + 1
        int iPravy = 2 * aktualniKoren + 2; // right = 2*i + 2

        // If left child is larger than root
        if (iLevy < velikostPole && komparator.compare(pole[iLevy], pole[iNejmensi]) < 0) {
            iNejmensi = iLevy;
        }

        // If right child is larger than largest so far
        if (iPravy < velikostPole && komparator.compare(pole[iPravy], pole[iNejmensi]) < 0) {
            iNejmensi = iPravy;
        }

        // If largest is not root
        if (iNejmensi != aktualniKoren) {
            T tmp = pole[aktualniKoren];
            pole[aktualniKoren] = pole[iNejmensi];
            pole[iNejmensi] = tmp;

            // Recursively heapify the affected sub-tree
            heapify(pole, velikostPole, iNejmensi);
        }
    }
}
