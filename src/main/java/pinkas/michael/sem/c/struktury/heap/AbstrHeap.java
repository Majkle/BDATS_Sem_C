package pinkas.michael.sem.c.struktury.heap;

import java.util.Arrays;
import java.util.Iterator;

public class AbstrHeap<T extends Comparable<T>> implements IAbstrHeap<T> {

    private T[] prioritniFronta;

    private void _prebuduj() {
        int velikostPole = prioritniFronta.length;
        // Index of last non-leaf node
        int polovinaPole = velikostPole / 2;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int aktualniKoren = polovinaPole - 1; aktualniKoren >= 0; aktualniKoren--) {
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
    private void heapify(T pole[], int velikostPole, int aktualniKoren) {
        int iNejmensi = aktualniKoren; // Initialize largest as root
        int iLevy = 2 * aktualniKoren + 1; // left = 2*i + 1
        int iPravy = 2 * aktualniKoren + 2; // right = 2*i + 2

        // If left child is larger than root
        if (iLevy < velikostPole && pole[iLevy].compareTo(pole[iNejmensi]) < 0)
            iNejmensi = iLevy;

        // If right child is larger than largest so far
        if (iPravy < velikostPole && pole[iPravy].compareTo(pole[iNejmensi]) < 0)
            iNejmensi = iPravy;

        // If largest is not root
        if (iNejmensi != aktualniKoren) {
            T tmp = pole[aktualniKoren];
            pole[aktualniKoren] = pole[iNejmensi];
            pole[iNejmensi] = tmp;

            // Recursively heapify the affected sub-tree
            heapify(pole, velikostPole, iNejmensi);
        }
    }

    @Override
    public void vybuduj(T[] zamky) {
        prioritniFronta = zamky;
        _prebuduj();
    }

    @Override
    public void prebuduj() {
        _prebuduj();
    }

    @Override
    public void zrus() {
        prioritniFronta = null;
    }

    @Override
    public boolean jePrazdny() {
        return prioritniFronta == null || prioritniFronta.length == 0;
    }

    @Override
    public void vloz(T vkladany) {
        prioritniFronta = Arrays.copyOf(prioritniFronta, prioritniFronta.length + 1);
        prioritniFronta[prioritniFronta.length - 1] = vkladany;
    }

    @Override
    public T odeberMax() {
        T odebirany = prioritniFronta[prioritniFronta.length - 1];
        prioritniFronta = Arrays.copyOf(prioritniFronta, prioritniFronta.length - 1);
        return odebirany;
    }

    @Override
    public T zpristupniMax() {
        return prioritniFronta[prioritniFronta.length - 1];
    }

    @Override
    public Iterator<T> iterator(eTypProhl typProhlidky) {
        return Arrays.stream(prioritniFronta).iterator();
    }
}
