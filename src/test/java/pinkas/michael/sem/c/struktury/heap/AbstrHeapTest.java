package pinkas.michael.sem.c.struktury.heap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pinkas.michael.sem.c.TestData;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AbstrHeapTest {
    private TestData[] data;
    private AbstrHeap<TestData> abstrHeap;


    private final TestData T0 = new TestData(0);
    private final TestData T1 = new TestData(1);
    private final TestData T2 = new TestData(2);
    private final TestData T3 = new TestData(3);
    private final TestData T4 = new TestData(4);
    private final TestData T5 = new TestData(5);

    private final Comparator<TestData> komparator = new Comparator<TestData>() {
        @Override
        public int compare(TestData o1, TestData o2) {
            return o1.compareTo(o2);
        }
    };

    @Before
    public void setUp() {
        abstrHeap = new AbstrHeap<>(komparator);
    }


    @After
    public void tearDown() {
        abstrHeap = null;
    }

    @Rule
    public final ExpectedException ocekavanaVyjimka = ExpectedException.none();


    private boolean jeOcekavanyObsahFronty(TestData[] ocekavany, AbstrHeap<TestData> vysledek) {

        Iterator<TestData> it = vysledek.iterator(eTypProhl.SIRKA);
        it.forEachRemaining(System.out::println);

        Iterator<TestData> iterator = vysledek.iterator(eTypProhl.SIRKA);

        for (int i = 0; i < ocekavany.length; i++) {
            if (ocekavany[i].compareTo(iterator.next()) != 0) {
                return false;
            }
        }

        return !iterator.hasNext();
    }

    @Test
    public void abstrHeapTest_vybuduj_zadnaData_1() {
        ocekavanaVyjimka.expect(AbstrHeapException.class);
        ocekavanaVyjimka.expectMessage("Žádná data pro vložení");

        abstrHeap.vybuduj(null);
    }

    @Test
    public void abstrHeapTest_vybuduj_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T0;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vybuduj_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T2;
        data[2] = T1;

        abstrHeap.vybuduj(data);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
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

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
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

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
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


        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_prazdny_1() {
        abstrHeap.prebuduj();

        assertTrue(abstrHeap.jePrazdny());
    }

    @Test
    public void abstrHeapTest_prebuduj_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T3);
        abstrHeap.prebuduj();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T3;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T0);
        abstrHeap.prebuduj();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_prebuduj_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T3;
        data[2] = T1;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T2);
        abstrHeap.prebuduj();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T3, T2, T1, T0}, abstrHeap));
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
        abstrHeap.prebuduj();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
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
        abstrHeap.prebuduj();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
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
        abstrHeap.prebuduj();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zrus_1() {
        abstrHeap.zrus();

        assertTrue(abstrHeap.jePrazdny());
    }

    @Test
    public void abstrHeapTest_zrus_2() {
        abstrHeap.vloz(T0);

        abstrHeap.zrus();

        assertTrue(abstrHeap.jePrazdny());
    }

    @Test
    public void abstrHeapTest_jePrazdny_1() {
        assertTrue(abstrHeap.jePrazdny());
    }

    @Test
    public void abstrHeapTest_jePrazdny_2() {
        abstrHeap.vloz(T0);

        assertFalse(abstrHeap.jePrazdny());
    }

    @Test
    public void abstrHeapTest_vloz_prazdny_1() {
        abstrHeap.vloz(T0);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vloz_zadna_data_1() {
        ocekavanaVyjimka.expect(AbstrHeapException.class);
        ocekavanaVyjimka.expectMessage("Žádná data pro vložení");

        abstrHeap.vloz(null);
    }

    @Test
    public void abstrHeapTest_vloz_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T3);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vloz_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T3;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T0);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vloz_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T3;
        data[2] = T1;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T2);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vloz_4() {
        data = new TestData[5];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;
        data[3] = T3;
        data[4] = T4;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T5);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vloz_5() {
        data = new TestData[5];

        data[0] = T5;
        data[1] = T4;
        data[2] = T2;
        data[3] = T1;
        data[4] = T0;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T3);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_vloz_6() {
        data = new TestData[5];

        data[0] = T1;
        data[1] = T3;
        data[2] = T4;
        data[3] = T2;
        data[4] = T5;

        abstrHeap.vybuduj(data);

        abstrHeap.vloz(T0);

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_odeberMax_prazdny_1() {
        assertNull(abstrHeap.odeberMax());
    }

    @Test
    public void abstrHeapTest_odeberMax_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);
        abstrHeap.odeberMax();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_odeberMax_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T0;

        abstrHeap.vybuduj(data);
        abstrHeap.odeberMax();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_odeberMax_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T2;
        data[2] = T1;

        abstrHeap.vybuduj(data);
        abstrHeap.odeberMax();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_odeberMax_4() {
        data = new TestData[6];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;
        data[3] = T3;
        data[4] = T4;
        data[5] = T5;

        abstrHeap.vybuduj(data);
        abstrHeap.odeberMax();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_odeberMax_5() {
        data = new TestData[6];

        data[0] = T5;
        data[1] = T4;
        data[2] = T3;
        data[3] = T2;
        data[4] = T1;
        data[5] = T0;

        abstrHeap.vybuduj(data);
        abstrHeap.odeberMax();

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_odeberMax_6() {
        data = new TestData[6];

        data[0] = T1;
        data[1] = T3;
        data[2] = T4;
        data[3] = T2;
        data[4] = T5;
        data[5] = T0;

        abstrHeap.vybuduj(data);
        abstrHeap.odeberMax();


        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zpristpniMax_prazdny_1() {
        assertNull(abstrHeap.zpristupniMax());
    }

    @Test
    public void abstrHeapTest_zpristupniMax_1() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;

        abstrHeap.vybuduj(data);

        assertEquals(T0, abstrHeap.zpristupniMax());
        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zpristupniMax_2() {
        data = new TestData[3];

        data[0] = T2;
        data[1] = T1;
        data[2] = T0;

        abstrHeap.vybuduj(data);

        assertEquals(T0, abstrHeap.zpristupniMax());
        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zpristupniMax_3() {
        data = new TestData[3];

        data[0] = T0;
        data[1] = T2;
        data[2] = T1;

        abstrHeap.vybuduj(data);

        assertEquals(T0, abstrHeap.zpristupniMax());
        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zpristupniMax_4() {
        data = new TestData[6];

        data[0] = T0;
        data[1] = T1;
        data[2] = T2;
        data[3] = T3;
        data[4] = T4;
        data[5] = T5;

        abstrHeap.vybuduj(data);

        assertEquals(T0, abstrHeap.zpristupniMax());
        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zpristupniMax_5() {
        data = new TestData[6];

        data[0] = T5;
        data[1] = T4;
        data[2] = T3;
        data[3] = T2;
        data[4] = T1;
        data[5] = T0;

        abstrHeap.vybuduj(data);

        assertEquals(T0, abstrHeap.zpristupniMax());
        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_zpristupniMax_6() {
        data = new TestData[6];

        data[0] = T1;
        data[1] = T3;
        data[2] = T4;
        data[3] = T2;
        data[4] = T5;
        data[5] = T0;

        abstrHeap.vybuduj(data);

        assertEquals(T0, abstrHeap.zpristupniMax());
        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_sirka_1() {
        abstrHeap.vloz(T0);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.SIRKA);

        assertEquals(T0, it.next());

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_sirka_2() {
        abstrHeap.vloz(T1);
        abstrHeap.vloz(T0);
        abstrHeap.vloz(T2);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.SIRKA);

        assertEquals(T2, it.next());
        assertEquals(T1, it.next());
        assertEquals(T0, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_sirka_3() {
        abstrHeap.vloz(T3);
        abstrHeap.vloz(T1);
        abstrHeap.vloz(T4);
        abstrHeap.vloz(T0);
        abstrHeap.vloz(T2);
        abstrHeap.vloz(T5);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.SIRKA);

        assertEquals(T5, it.next());
        assertEquals(T4, it.next());
        assertEquals(T3, it.next());
        assertEquals(T2, it.next());
        assertEquals(T1, it.next());
        assertEquals(T0, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_sirka_4() {
        abstrHeap.vloz(T3);
        abstrHeap.vloz(T1);
        abstrHeap.vloz(T4);
        abstrHeap.vloz(T0);
        abstrHeap.vloz(T2);
        abstrHeap.vloz(T5);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.SIRKA);

        while (it.hasNext()) {
            it.next();
        }

        assertFalse(it.hasNext());
    }

    @Test
    public void abstrHeapTest_iterator_hloubka_prazdnyStrom() {
        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.HLOUBKA);

        assertNull(it);
    }

    @Test
    public void abstrHeapTest_iterator_hloubka_neniDalsi() {
        ocekavanaVyjimka.expect(NoSuchElementException.class);
        ocekavanaVyjimka.expectMessage("Žádný další klíč");

        abstrHeap.vloz(T0);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.HLOUBKA);

        it.next();
        it.next();
    }

    @Test
    public void abstrHeapTest_iterator_hloubka_1() {
        abstrHeap.vloz(T0);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.HLOUBKA);

        assertEquals(T0, it.next());

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_hloubka_2() {
        abstrHeap.vloz(T1);
        abstrHeap.vloz(T0);
        abstrHeap.vloz(T2);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.HLOUBKA);

        assertEquals(T1, it.next());
        assertEquals(T2, it.next());
        assertEquals(T0, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_hloubka_3() {
        abstrHeap.vloz(T3);
        abstrHeap.vloz(T1);
        abstrHeap.vloz(T4);
        abstrHeap.vloz(T0);
        abstrHeap.vloz(T2);
        abstrHeap.vloz(T5);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.HLOUBKA);

        assertEquals(T2, it.next());
        assertEquals(T4, it.next());
        assertEquals(T1, it.next());
        assertEquals(T5, it.next());
        assertEquals(T0, it.next());
        assertEquals(T3, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahFronty(new TestData[]{T5, T4, T3, T2, T1, T0}, abstrHeap));
    }

    @Test
    public void abstrHeapTest_iterator_hloubka_4() {
        abstrHeap.vloz(T3);
        abstrHeap.vloz(T1);
        abstrHeap.vloz(T4);
        abstrHeap.vloz(T0);
        abstrHeap.vloz(T2);
        abstrHeap.vloz(T5);

        Iterator<TestData> it = abstrHeap.iterator(eTypProhl.SIRKA);

        while (it.hasNext()) {
            it.next();
        }

        assertFalse(it.hasNext());
    }
}
