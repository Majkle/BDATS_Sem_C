package pinkas.michael.sem.c.struktury.bvs;

import org.junit.*;
import org.junit.rules.ExpectedException;
import pinkas.michael.sem.c.TestDataKeyValue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AbstrTableTest {

    private AbstrTable<Integer, Integer> abstrTable;


    private final TestDataKeyValue T00 = new TestDataKeyValue(0, 0);
    private final TestDataKeyValue T11 = new TestDataKeyValue(1, 1);
    private final TestDataKeyValue T22 = new TestDataKeyValue(2, 2);
    private final TestDataKeyValue T33 = new TestDataKeyValue(3, 3);
    private final TestDataKeyValue T44 = new TestDataKeyValue(4, 4);
    private final TestDataKeyValue T55 = new TestDataKeyValue(5, 5);
    private final TestDataKeyValue T36 = new TestDataKeyValue(3, 6);

    @Before
    public void setUp() {
        abstrTable = new AbstrTable<>();
    }

    @After
    public void tearDown() {
        abstrTable = null;
    }

    @Rule
    public final ExpectedException ocekavanaVyjimka = ExpectedException.none();

    private static boolean jeOcekavanyObsahStromu(TestDataKeyValue[] ocekvany, AbstrTable<Integer, Integer> vysledek) {

        vysledek.vypisStromu();

        Iterator<TestDataKeyValue> itOcekavany = Arrays.stream(ocekvany).iterator();
        Iterator<Integer> itVysledek = vysledek.vytvorIterator(eTypProhl.SIRKA);

        while (itOcekavany.hasNext()) {
            Integer o = itOcekavany.next().value;
            Integer v = itVysledek.next();

            if (!o.equals(v)) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void abstrTableTest_zrus_prazdny() {
        abstrTable.zrus();

        assertTrue(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_zrus_zaplneny_1() {
        abstrTable.vloz(T11.key, T11.value);

        abstrTable.zrus();

        assertTrue(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_zrus_zaplneny_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T22.key, T33.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T22.key, T22.value);

        abstrTable.zrus();

        assertTrue(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_jePrazdny_prazdny() {
        assertTrue(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_jePrazdny_zaplneny_1() {
        abstrTable.vloz(T11.key, T11.value);

        assertFalse(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_jePrazdny_zaplneny_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T22.key, T33.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T22.key, T22.value);

        assertFalse(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_vloz_zadnyKlic_1() {
        ocekavanaVyjimka.expect(AbstrTableException.class);
        ocekavanaVyjimka.expectMessage("Žádný klíč");

        abstrTable.vloz(null, T00.value);
    }

    @Test
    public void abstrTableTest_vloz_zadnyKlic_2() {
        ocekavanaVyjimka.expect(AbstrTableException.class);
        ocekavanaVyjimka.expectMessage("Žádný klíč");

        abstrTable.vloz(null, null);
    }

    @Test
    public void abstrTableTest_vloz_zadnyHodnota() {
        ocekavanaVyjimka.expect(AbstrTableException.class);
        ocekavanaVyjimka.expectMessage("Žádná hodnota pro vložení");

        abstrTable.vloz(T00.key, null);
    }

    @Test
    public void abstrTableTest_vloz_1() {
        abstrTable.vloz(T11.key, T11.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T33.key, T33.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T22, T33}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_3() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T22.key, T22.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T33, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_4() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T22.key, T22.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T11, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_5() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T55.key, T55.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T33, T22, T44, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_6() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T55.key, T55.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T33.key, T33.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T55, T22, T44, T33}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_7() {
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T55.key, T55.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T44, T33, T55, T22, T11}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_8() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T55.key, T55.value);
        abstrTable.vloz(T22.key, T22.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T11, T44, T22, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_stejnyKlic_1() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T36.key, T36.value);
        abstrTable.vloz(T55.key, T55.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T11, T55, T00}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_stejnyKlic_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T36.key, T36.value);
        abstrTable.vloz(T55.key, T55.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T00, T33, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_vloz_stejnyKlic_3() {
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T55.key, T55.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T36.key, T36.value);

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T44, T33, T55, T00, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_najdi_zadnyKlic() {
        assertNull(abstrTable.najdi(null));
    }

    @Test
    public void abstrTableTest_najdi_prazdny() {
        assertNull(abstrTable.najdi(T00.key));
    }

    @Test
    public void abstrTableTest_najdi_zaplneny_1() {
        abstrTable.vloz(T00.key, T00.value);

        assertEquals(T00.value, abstrTable.najdi(T00.key));
    }

    @Test
    public void abstrTableTest_najdi_zaplneny_2() {
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T11.key, T11.value);

        assertEquals(T11.value, abstrTable.najdi(T11.key));
    }

    @Test
    public void abstrTableTest_najdi_zaplneny_3() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);

        assertEquals(T00.value, abstrTable.najdi(T00.key));
    }

    @Test
    public void abstrTableTest_najdi_zaplneny_4() {
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T55.key, T55.value);

        assertEquals(T00.value, abstrTable.najdi(T00.key));
        assertEquals(T11.value, abstrTable.najdi(T11.key));
        assertEquals(T22.value, abstrTable.najdi(T22.key));
        assertEquals(T33.value, abstrTable.najdi(T33.key));
        assertEquals(T44.value, abstrTable.najdi(T44.key));
        assertEquals(T55.value, abstrTable.najdi(T55.key));
    }

    @Test
    public void abstrTableTest_najdi_zaplneny_5() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        assertEquals(T00.value, abstrTable.najdi(T00.key));
        assertEquals(T11.value, abstrTable.najdi(T11.key));
        assertEquals(T22.value, abstrTable.najdi(T22.key));
        assertEquals(T33.value, abstrTable.najdi(T33.key));
        assertEquals(T44.value, abstrTable.najdi(T44.key));
        assertEquals(T55.value, abstrTable.najdi(T55.key));
    }

    @Test
    public void abstrTableTest_odeber_zadnyKlic() {
        assertNull(abstrTable.odeber(null));
    }

    @Test
    public void abstrTableTest_odeber_prazdny() {
        assertNull(abstrTable.odeber(T00.key));

        assertTrue(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_odeber_zadnaData() {
        abstrTable.vloz(T00.key, T00.value);

        assertNull(abstrTable.odeber(null));
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_1() {
        abstrTable.vloz(T00.key, T00.value);

        assertEquals(T00.value, abstrTable.odeber(T00.key));
        assertTrue(abstrTable.jePrazdny());
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);

        assertEquals(T00.value, abstrTable.odeber(T00.key));
        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_3() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);

        assertEquals(T22.value, abstrTable.odeber(T22.key));
        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T00}, abstrTable));
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_4() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);

        assertEquals(T11.value, abstrTable.odeber(T11.key));
        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T00, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_5() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        assertEquals(T44.value, abstrTable.odeber(T44.key));

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T11, T55, T00, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_6() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        assertEquals(T11.value, abstrTable.odeber(T11.key));

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T00, T44, T22, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_odeber_zaplneny_7() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        assertEquals(T33.value, abstrTable.odeber(T33.key));

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T22, T11, T44, T00, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_sirka_prazdnyStrom() {
        ocekavanaVyjimka.expect(NoSuchElementException.class);
        ocekavanaVyjimka.expectMessage("Žádný další klíč");


        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void abstrTableTest_iterator_sirka_neniDalsi() {
        ocekavanaVyjimka.expect(NoSuchElementException.class);
        ocekavanaVyjimka.expectMessage("Žádný další klíč");

        abstrTable.vloz(T00.key, T00.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        it.next();
        it.next();
    }

    @Test
    public void abstrTableTest_iterator_sirka_1() {
        abstrTable.vloz(T00.key, T00.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(T00.value, it.next());

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T00}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_sirka_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(T11.value, it.next());
        assertEquals(T00.value, it.next());
        assertEquals(T22.value, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T00, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_sirka_3() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        assertEquals(T33.value, it.next());
        assertEquals(T11.value, it.next());
        assertEquals(T44.value, it.next());
        assertEquals(T00.value, it.next());
        assertEquals(T22.value, it.next());
        assertEquals(T55.value, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T11, T44, T00, T22, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_sirka_4() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        while (it.hasNext()) {
            it.next();
        }

        assertFalse(it.hasNext());
    }

    @Test
    public void abstrTableTest_iterator_hloubka_prazdnyStrom() {
        ocekavanaVyjimka.expect(NoSuchElementException.class);
        ocekavanaVyjimka.expectMessage("Žádný další klíč");


        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.HLOUBKA);

        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void abstrTableTest_iterator_hloubka_neniDalsi() {
        ocekavanaVyjimka.expect(NoSuchElementException.class);
        ocekavanaVyjimka.expectMessage("Žádný další klíč");

        abstrTable.vloz(T00.key, T00.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.HLOUBKA);

        it.next();
        it.next();
    }

    @Test
    public void abstrTableTest_iterator_hloubka_1() {
        abstrTable.vloz(T00.key, T00.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.HLOUBKA);

        assertEquals(T00.value, it.next());

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T00}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_hloubka_2() {
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.HLOUBKA);

        assertEquals(T00.value, it.next());
        assertEquals(T11.value, it.next());
        assertEquals(T22.value, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T11, T00, T22}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_hloubka_3() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.HLOUBKA);

        assertEquals(T00.value, it.next());
        assertEquals(T11.value, it.next());
        assertEquals(T22.value, it.next());
        assertEquals(T33.value, it.next());
        assertEquals(T44.value, it.next());
        assertEquals(T55.value, it.next());
        assertFalse(it.hasNext());

        assertTrue(jeOcekavanyObsahStromu(new TestDataKeyValue[]{T33, T11, T44, T00, T22, T55}, abstrTable));
    }

    @Test
    public void abstrTableTest_iterator_hloubka_4() {
        abstrTable.vloz(T33.key, T33.value);
        abstrTable.vloz(T11.key, T11.value);
        abstrTable.vloz(T44.key, T44.value);
        abstrTable.vloz(T00.key, T00.value);
        abstrTable.vloz(T22.key, T22.value);
        abstrTable.vloz(T55.key, T55.value);

        Iterator<Integer> it = abstrTable.vytvorIterator(eTypProhl.SIRKA);

        while (it.hasNext()) {
            it.next();
        }

        assertFalse(it.hasNext());
    }
}