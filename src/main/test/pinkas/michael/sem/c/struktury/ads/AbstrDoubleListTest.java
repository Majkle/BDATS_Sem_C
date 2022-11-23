package pinkas.michael.sem.c.struktury.ads;

import org.junit.*;
import org.junit.rules.ExpectedException;
import pinkas.michael.sem.c.TestData;
import pinkas.michael.sem.c.struktury.adl.AbstrDoubleList;
import pinkas.michael.sem.c.struktury.adl.AbstrDoubleListException;
import pinkas.michael.sem.c.struktury.adl.IAbstrDoubleList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AbstrDoubleListTest {

    private AbstrDoubleList<TestData> abstrDoubleList;


    private final TestData T0 = new TestData(0);
    private final TestData T1 = new TestData(1);
    private final TestData T2 = new TestData(2);
    private final TestData T3 = new TestData(3);

    @Before
    public void setUp() {
        abstrDoubleList = new AbstrDoubleList<>();
    }

    @After
    public void tearDown() {
        abstrDoubleList = null;
    }

    @Rule
    public final ExpectedException ocekavanaVyjimka = ExpectedException.none();

    private static boolean jeOcekavanyObsahListu(TestData[] ocekvany, IAbstrDoubleList<TestData> vysledek) {
        Iterator<TestData> itOcekavany = Arrays.stream(ocekvany).iterator();
        Iterator<TestData> itVysledek = vysledek.iterator();

        while (itOcekavany.hasNext()) {
            TestData o = itOcekavany.next();
            TestData v = itVysledek.next();

            if (!o.equals(v)) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void abstrDoubleListTest_zrus_1() {
        abstrDoubleList.zrus();

        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_zrus_2() {
        abstrDoubleList.vlozPrvni(T0);

        abstrDoubleList.zrus();

        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_zrus_3() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniNaslednika();

        abstrDoubleList.odeberPosledni();

        abstrDoubleList.zrus();

        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_jePrazdny_1() {
        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_jePrazdny_2() {
        abstrDoubleList.vlozPrvni(T0);

        Assert.assertFalse(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_vlozPrvni_ZadnaData() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Žádná data");

        abstrDoubleList.vlozPrvni(null);
    }
    @Test
    public void abstrDoubleListTest_vlozPrvni_1Prvek() {
        abstrDoubleList.vlozPrvni(T0);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_vlozPrvni_2Prvky() {

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);


        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1, T0}, abstrDoubleList));
        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_vlozPrvni_3Prvky() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPrvni(T2);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T2, T1, T0}, abstrDoubleList));
        assertEquals(T2, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_vlozPrvni_4Prvky() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);
        abstrDoubleList.vlozPrvni(T3);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T3, T1, T0, T2}, abstrDoubleList));
        assertEquals(T3, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_vlozPosledni_ZadnaData() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Žádná data");

        abstrDoubleList.vlozPosledni(null);
    }

    @Test
    public void abstrDoubleListTest_vlozPosledni_1Prvek() {
        abstrDoubleList.vlozPosledni(T0);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
        assertEquals(T0, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_vlozPosledni_2Prvky() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T1}, abstrDoubleList));
        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_vlozPosledni_3Prvky() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T1, T2}, abstrDoubleList));
        assertEquals(T2, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_vlozNaslednika_ZadnaData() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Žádná data");

        abstrDoubleList.vlozNaslednika(null);
    }

    @Test
    public void abstrDoubleListTest_vlozNaslednika_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.vlozNaslednika(T0);
    }

    @Test
    public void abstrDoubleListTest_vlozNaslednika_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);

        abstrDoubleList.vlozNaslednika(T1);
    }


    @Test
    public void abstrDoubleListTest_vlozNaslednika_aktualni_1() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPosledni();

        abstrDoubleList.vlozNaslednika(T1);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T1}, abstrDoubleList));
        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_vlozNaslednika_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPosledni();

        abstrDoubleList.vlozNaslednika(T2);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T1, T2}, abstrDoubleList));
        assertEquals(T2, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_vlozPredchudce_ZadnaData() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Žádná data");

        abstrDoubleList.vlozPredchudce(null);
    }

    @Test
    public void abstrDoubleListTest_vlozPredchudce_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.vlozPredchudce(T0);
    }

    @Test
    public void abstrDoubleListTest_vlozPredchudce_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);

        abstrDoubleList.vlozPredchudce(T1);
    }

    @Test
    public void abstrDoubleListTest_vlozPredchudce_aktualni_1() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPosledni();

        abstrDoubleList.vlozPredchudce(T1);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1, T0}, abstrDoubleList));
        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
        assertEquals(T0, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_vlozPredchudce_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPosledni();

        abstrDoubleList.vlozPredchudce(T2);

        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T2, T1}, abstrDoubleList));
        assertEquals(T2, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniAktualni_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.zpristupniAktualni();
    }


    @Test
    public void abstrDoubleListTest_zpristupniAktualni_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniAktualni();
    }

    @Test
    public void abstrDoubleListTest_zpristupniAktualni_aktualni_1() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.zpristupniPrvni();
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_1() {
        abstrDoubleList.vlozPrvni(T0);

        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);

        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_4() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_5() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_6() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_7() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPrvni(T2);

        assertEquals(T2, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_8() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_9() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPrvni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T2, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_neniAktualni() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.zpristupniPosledni();
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_1() {
        abstrDoubleList.vlozPrvni(T0);

        assertEquals(T0, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);

        assertEquals(T0, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_4() {
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPrvni(T0);

        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_5() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        assertEquals(T0, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_6() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_7() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        assertEquals(T2, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_8() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPrvni(T2);

        assertEquals(T0, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPosledni_aktualni_9() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPosledni();

        assertEquals(T0, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPrvni_aktualni_10() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPrvni(T2);

        abstrDoubleList.zpristupniPosledni();

        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.zpristupniNaslednika();
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniNaslednika();
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_1() {
        abstrDoubleList.vlozPrvni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_4() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_5() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_6() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_7() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_8() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniNaslednika());
    }

    @Test
    public void abstrDoubleListTest_zpristupniNaslednika_aktualni_9() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniNaslednika();

        assertEquals(T0, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.zpristupniPredchudce();
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPredchudce();
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_1() {
        abstrDoubleList.vlozPrvni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_4() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_5() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_6() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_7() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T2, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_8() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T2, abstrDoubleList.zpristupniPredchudce());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_9() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniPredchudce();

        assertEquals(T2, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_10() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniPredchudce();
        abstrDoubleList.zpristupniPredchudce();

        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_11() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);
        abstrDoubleList.vlozPosledni(T3);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniPredchudce();
        abstrDoubleList.zpristupniPredchudce();
        abstrDoubleList.zpristupniPredchudce();

        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_zpristupniPredchudce_aktualni_12() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPrvni(T2);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniPredchudce();
        abstrDoubleList.zpristupniPredchudce();

        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.odeberAktualni();
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.odeberAktualni();
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_neniAktualni_3() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.odeberAktualni();

        abstrDoubleList.zpristupniAktualni();
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_aktualni_1() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberAktualni());
        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberAktualni());
        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_aktualni_3() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPosledni();

        assertEquals(T1, abstrDoubleList.odeberAktualni());
        assertEquals(T0, abstrDoubleList.zpristupniAktualni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberAktualni_aktualni_4() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberAktualni());
        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_odeberPrvni_neniList_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.odeberPrvni();
    }

    @Test
    public void abstrDoubleListTest_odeberPrvni_neniList_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.zpristupniPrvni();

        abstrDoubleList.odeberPrvni();

        abstrDoubleList.zpristupniAktualni();
    }

    @Test
    public void abstrDoubleListTest_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);

        assertEquals(T1, abstrDoubleList.odeberPrvni());
        abstrDoubleList.zpristupniAktualni();
    }

    @Test
    public void abstrDoubleListTest_odeberPrvni_1() {
        abstrDoubleList.vlozPosledni(T0);

        assertEquals(T0, abstrDoubleList.odeberPrvni());
        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_odeberPrvni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        assertEquals(T0, abstrDoubleList.odeberPrvni());
        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPrvni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        assertEquals(T1, abstrDoubleList.odeberPrvni());
        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPosledni_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.odeberPosledni();
    }

    @Test
    public void abstrDoubleListTest_odeberPosledni_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.zpristupniPrvni();

        abstrDoubleList.odeberPosledni();

        abstrDoubleList.zpristupniAktualni();
    }

    @Test
    public void abstrDoubleListTest_odeberPosledni_1() {
        abstrDoubleList.vlozPosledni(T0);

        assertEquals(T0, abstrDoubleList.odeberPosledni());
        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_odeberPosledni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        assertEquals(T1, abstrDoubleList.odeberPosledni());
        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPosledni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        assertEquals(T0, abstrDoubleList.odeberPosledni());
        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPosledni_4() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);
        abstrDoubleList.vlozPosledni(T2);

        assertEquals(T2, abstrDoubleList.odeberPosledni());
        assertEquals(T0, abstrDoubleList.zpristupniAktualni());
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.odeberNaslednika();
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.odeberNaslednika();
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_aktualni_1() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberNaslednika());
        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.odeberNaslednika());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_aktualni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberNaslednika());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_aktualni_4() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.odeberNaslednika());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T2}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_aktualni_5() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();
        abstrDoubleList.zpristupniNaslednika();


        assertEquals(T2, abstrDoubleList.odeberNaslednika());
        assertEquals(T0, abstrDoubleList.zpristupniPrvni());
        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0,T1},abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberNaslednika_aktualni_6() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPosledni();

        assertEquals(T0, abstrDoubleList.odeberNaslednika());
        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_neniAktualni_1() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.odeberPredchudce();
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_neniAktualni_2() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Není nastaven aktuální prvek");

        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.odeberPredchudce();
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_aktualni_1() {
        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberPredchudce());
        Assert.assertTrue(abstrDoubleList.jePrazdny());
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T1, abstrDoubleList.odeberPredchudce());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_aktualni_3() {
        abstrDoubleList.vlozPrvni(T0);
        abstrDoubleList.vlozPrvni(T1);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeberPredchudce());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_aktualni_4() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T2, abstrDoubleList.odeberPredchudce());
        Assert.assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_aktualni_5() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T2, abstrDoubleList.odeberPredchudce());
        assertEquals(T1, abstrDoubleList.zpristupniPosledni());
    }

    @Test
    public void abstrDoubleListTest_odeberPredchudce_aktualni_6() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPosledni();
        abstrDoubleList.zpristupniPredchudce();

        assertEquals(T0, abstrDoubleList.odeberPredchudce());
        assertEquals(T1, abstrDoubleList.zpristupniPrvni());
    }

    @Test
    public void abstrDoubleListTest_odeber_neniAktualni() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("List je prázdný");

        abstrDoubleList.odeber(T0);
    }

    @Test
    public void abstrDoubleListTest_odeber_zadnaData() {
        ocekavanaVyjimka.expect(AbstrDoubleListException.class);
        ocekavanaVyjimka.expectMessage("Žádná data");

        abstrDoubleList.vlozPosledni(T0);

        abstrDoubleList.odeber(null);
    }

    @Test
    public void abstrDoubleListTest_odeber_aktualni_1() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        assertEquals(T0, abstrDoubleList.odeber(T0));
        assertTrue(jeOcekavanyObsahListu(new TestData[]{T1, T2}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeber_aktualni_2() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        assertEquals(T2, abstrDoubleList.odeber(T2));
        assertTrue(jeOcekavanyObsahListu(new TestData[]{T0, T1}, abstrDoubleList));
    }

    @Test
    public void abstrDoubleListTest_odeber_aktualni_3() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        abstrDoubleList.zpristupniPrvni();

        assertEquals(T0, abstrDoubleList.odeber(T0));
        assertEquals(T1, abstrDoubleList.zpristupniAktualni());
        assertTrue(jeOcekavanyObsahListu(new TestData[]{T1, T2}, abstrDoubleList));
    }

    @Test(expected = NoSuchElementException.class)
    public void abstrDoubleList_iterator_1() {
        Iterator<TestData> it = abstrDoubleList.iterator();

        it.next();
    }

    @Test
    public void abstrDoubleList_iterator_2() {
        Iterator<TestData> it = abstrDoubleList.iterator();

        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void abstrDoubleList_iterator_3() {
        abstrDoubleList.vlozPosledni(T0);

        Iterator<TestData> it = abstrDoubleList.iterator();

        Assert.assertTrue(it.hasNext());
        assertEquals(T0, it.next());
    }

    @Test
    public void abstrDoubleList_iterator_4() {
        abstrDoubleList.vlozPosledni(T0);
        abstrDoubleList.vlozPosledni(T1);
        abstrDoubleList.vlozPosledni(T2);

        Iterator<TestData> it = abstrDoubleList.iterator();

        assertEquals(T0, it.next());
        assertEquals(T1, it.next());
        assertEquals(T2, it.next());
        Assert.assertFalse(it.hasNext());
    }
}