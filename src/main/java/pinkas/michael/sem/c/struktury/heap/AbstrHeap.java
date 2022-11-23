package pinkas.michael.sem.c.struktury.heap;

import pinkas.michael.sem.c.data.Zamek;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class AbstrHeap<T extends Comparable<T>> implements IAbstrHeap<T> {

    private T[] prioritniFronta;


    @Override
    public void vybuduj(T[] zamky) {
        _vybuduj(zamky);
    }

    @Override
    public void prebuduj() {
        _vybuduj(prioritniFronta);
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

    private void _vybuduj(T[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }

        prioritniFronta = arr;
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(T arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l].compareTo(arr[largest]) > 0)
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[l].compareTo(arr[largest]) > 0)
            largest = r;

        // If largest is not root
        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}
