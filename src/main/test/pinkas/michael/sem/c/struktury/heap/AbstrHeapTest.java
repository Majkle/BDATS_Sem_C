package pinkas.michael.sem.c.struktury.heap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pinkas.michael.sem.c.TestData;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class AbstrHeapTest {
    private TestData[] data;
    private AbstrHeap<TestData> abstrHeap;


    private final TestData T0 = new TestData(0);
    private final TestData T1 = new TestData(1);
    private final TestData T2 = new TestData(2);
    private final TestData T3 = new TestData(3);
    private final TestData T4 = new TestData(3);
    private final TestData T5 = new TestData(3);

    @Before
    public void setUp() {
        abstrHeap = new AbstrHeap<>();
    }


    @After
    public void tearDown() {
        abstrHeap = null;
    }

    private boolean jeOcekavanyObsahFronty(TestData[] ocekavany, AbstrHeap<TestData> vysledek) {
        Iterator<TestData> iterator = vysledek.iterator(null);
        for (int i = 0; i < ocekavany.length; i++) {

            System.out.println(ocekavany[i]);
            if (ocekavany[i].compareTo(iterator.next()) != 0) {
                System.out.println("xd");
                return false;
            }
        }

        return !iterator.hasNext();
    }

    @Test
    public void abstrHeapTest_vybuduj_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T0;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T1, T2, T3}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T2;
        data[2] = T1;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T1, T2, T3}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_4() {
        data = new TestData[6];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;
        data[3] = T3;
        data[4] = T4;
        data[5] = T5;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2, T3, T4, T5}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_5() {
        data = new TestData[6];

        data[0] = T5;
        data[1] = T4;
        data[2] = T3;
        data[3] = T2;
        data[4] = T1;
        data[5] = T0;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2, T3, T4, T5}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_6() {
        data = new TestData[6];

        data[0] = T1;
        data[1] = T3;
        data[2] = T4;
        data[3] = T2;
        data[4] = T5;
        data[5] = T0;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2, T3, T4, T5}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T3);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2,T3}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T3;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T0);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2,T3}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T3;
        data[2] = T1;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T2);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2,T3}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_4() {
        data = new TestData[5];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;
        data[3] = T3;
        data[4] = T4;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T5);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2, T3, T4, T5}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_5() {
        data = new TestData[5];

        data[0] = T5;
        data[1] = T4;
        data[2] = T2;
        data[3] = T1;
        data[4] = T0;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T3);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2, T3, T4, T5}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_6() {
        data = new TestData[5];

        data[0] = T1;
        data[1] = T3;
        data[2] = T4;
        data[3] = T2;
        data[4] = T5;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T0);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0, T1, T2, T3, T4, T5}, abstrHeap));
    }

    //TODO iterator
}
