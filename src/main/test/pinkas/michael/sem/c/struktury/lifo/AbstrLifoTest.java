package pinkas.michael.sem.c.struktury.lifo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pinkas.michael.sem.c.TestData;
import pinkas.michael.sem.c.struktury.lifo.AbstrLifo;
import pinkas.michael.sem.c.struktury.lifo.AbstrLifoException;
import pinkas.michael.sem.c.struktury.lifo.IAbstrLifo;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class AbstrLifoTest {


    private static final TestData T0 = new TestData(0);
    private static final TestData T1 = new TestData(0);
    private static final TestData T2 = new TestData(0);

    private IAbstrLifo<TestData> abstrLifo;

    @Before
    public void setUp() {
        abstrLifo = new AbstrLifo<>();
    }

    @After
    public void tearDown() {
        abstrLifo = null;
    }

    @Rule
    public final ExpectedException ocekavanaVyjimka = ExpectedException.none();

    @Test
    public void abstrLifoTest_zrus_1() {
        abstrLifo.zrus();

        assertTrue(abstrLifo.jePrazdny());
    }

    @Test
    public void abstrLifoTest_zrus_2() {
        abstrLifo.vloz(T0);
        abstrLifo.vloz(T1);
        abstrLifo.vloz(T2);

        abstrLifo.zrus();

        assertTrue(abstrLifo.jePrazdny());
    }

    private static boolean jeOcekavanyObsahZasobniku(TestData[] ocekavane, IAbstrLifo<TestData> vysledek) {
        Iterator<TestData> itOcekvane = Arrays.stream(ocekavane).iterator();

        while (itOcekvane.hasNext()) {
            if (!itOcekvane.next().equals(vysledek.odeber())) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void abstrLifoTest_jePrazdny_1() {
        assertTrue(abstrLifo.jePrazdny());
    }

    @Test
    public void abstrLifoTest_jePrazdny_2() {
        abstrLifo.vloz(T0);

        assertFalse(abstrLifo.jePrazdny());
    }

    @Test
    public void abstrLifoTest_jePrazdny_3() {
        abstrLifo.vloz(T0);
        abstrLifo.odeber();

        assertTrue(abstrLifo.jePrazdny());
    }

    @Test
    public void abstrLifoTest_vloz_prazdnaData_1() {
        ocekavanaVyjimka.expect(AbstrLifoException.class);
        ocekavanaVyjimka.expectMessage("Žádná data pro vložení");

        abstrLifo.vloz(null);
    }

    @Test
    public void abstrLifoTest_vloz_1prvek() {
        abstrLifo.vloz(T0);

        assertTrue(jeOcekavanyObsahZasobniku(new TestData[]{T0}, abstrLifo));
    }

    @Test
    public void abstrLifoTest_vloz_2prvky() {
        abstrLifo.vloz(T0);
        abstrLifo.vloz(T1);

        assertTrue(jeOcekavanyObsahZasobniku(new TestData[]{T1, T0}, abstrLifo));
    }

    @Test
    public void abstrLifoTest_vloz_3prvky() {
        abstrLifo.vloz(T0);
        abstrLifo.vloz(T1);
        abstrLifo.vloz(T2);

        assertTrue(jeOcekavanyObsahZasobniku(new TestData[]{T2, T1, T0}, abstrLifo));
    }

    @Test
    public void abstrLifoTest_odeber_prazdnaData_1() {
        ocekavanaVyjimka.expect(AbstrLifoException.class);
        ocekavanaVyjimka.expectMessage("Zásobník neobsahuje žádné prvky");

        abstrLifo.odeber();
    }

    @Test
    public void abstrLifoTest_odeber_1prvek() {
        abstrLifo.vloz(T0);

        assertEquals(T0, abstrLifo.odeber());
        assertTrue(abstrLifo.jePrazdny());
    }

    @Test
    public void abstrLifoTest_odeber_2prvek() {
        abstrLifo.vloz(T0);
        abstrLifo.vloz(T1);

        assertEquals(T1, abstrLifo.odeber());
        assertTrue(jeOcekavanyObsahZasobniku(new TestData[]{T0}, abstrLifo));
    }

    @Test
    public void abstrLifoTest_odeber_3prvek() {
        abstrLifo.vloz(T0);
        abstrLifo.vloz(T1);
        abstrLifo.vloz(T2);

        assertEquals(T2, abstrLifo.odeber());
        assertTrue(jeOcekavanyObsahZasobniku(new TestData[]{T1, T0}, abstrLifo));
    }
}
