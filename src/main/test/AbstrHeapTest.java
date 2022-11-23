import org.junit.jupiter.api.Test;
import pinkas.michael.sem.c.data.Zamek;
import pinkas.michael.sem.c.struktury.heap.AbstrHeap;

import java.util.Arrays;

public class AbstrHeapTest {
    @Test
    public void heapTestSort() {
        AbstrHeap heap = new AbstrHeap();

        int pocetZamku = 10;

        Zamek[] zamky = new Zamek[pocetZamku];
        for (int i = 0; i < pocetZamku; i++) {
            char letter = 'A' + 1;
            zamky[i] = new Zamek("" + letter, "" + letter);
        }

        System.out.println("PŘED");
        Arrays.toString(zamky);

        System.out.println("PO");
        heap.vybuduj(zamky);
        heap.vypis();
    }
}
