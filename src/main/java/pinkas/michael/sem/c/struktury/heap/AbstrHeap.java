package pinkas.michael.sem.c.struktury.heap;

import pinkas.michael.sem.c.data.Zamek;

import java.util.Arrays;
import java.util.Iterator;

public class AbstrHeap implements IAbstrHeap<Zamek> {

    private Zamek[] prioritniFronta;


    @Override
    public void vybuduj(Zamek[] zamky) {
        int n = zamky.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(zamky, n, i);
        }

        // Heap sort
        for (int i = n - 1; i >= 0; i--) {
            Zamek temp = zamky[0];
            zamky[0] = zamky[i];
            zamky[i] = temp;

            // Heapify root element
            heapify(zamky, i, 0);
        }

        prioritniFronta = zamky;
    }

    void heapify(Zamek arr[], int n, int i) {
        // Find largest among root, left child and right child
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        int porovnani = arr[l].getGps().compareTo(arr[largest].getGps());

        if (l < n && porovnani > 0)
            largest = l;

        if (r < n && porovnani > 0)
            largest = r;

        // Swap and continue heapifying if root is not largest
        if (largest != i) {
            Zamek swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public void vypis(){
        Arrays.toString(prioritniFronta);
    }

    @Override
    public void prebuduj() {
        _prebuduj();
    }

    private void _prebuduj() {

    }

    @Override
    public void zrus() {

    }

    @Override
    public boolean jePrazdny() {
        return false;
    }

    @Override
    public void vloz(Zamek vkladany) {

    }

    private void _vloz(Zamek vkladany) {

    }

    @Override
    public Zamek odeberMax() {
        return null;
    }

    @Override
    public Zamek zpristupniMax() {
        return null;
    }

    @Override
    public Iterator<Zamek> iterator(eTypProhl typProhlidky) {
        return null;
    }
}
