package pinkas.michael.sem.c.struktury.heap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pinkas.michael.sem.c.TestData;

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


    @Test
    public void abstrHeapTest_zpristupniMax() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T2;
        data[2] = T1;

        abstrHeap.vybuduj(data);

    }

    //TODO iterator
}
