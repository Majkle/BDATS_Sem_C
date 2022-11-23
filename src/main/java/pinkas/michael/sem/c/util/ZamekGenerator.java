package pinkas.michael.sem.c.util;

import pinkas.michael.sem.c.data.Zamek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ZamekGenerator {

    private static final int NAZVY_POCET;
    private static final int GPS_POCET;
    private static final String NAZVY = "PETROVICE U SEDLCAN;VELKE VSELISY;DOLNI KOUNICE;DRITEN;KOSTELEC NAD ORLICI;VALTICE;SINDELOVA;STENOVICE;CHYSE;HOLOVOUSY V PODKRKON;ROKYTNICE V ORLICKYC;SLATINANY;DOMOUSICE;KRHOV U HROTOVIC;TREBON;HOSTIVICE;KACEROV;ZIDLOCHOVICE;BOZEJOV;MECHOLUPY U PREDSLAV;MNICHOVO HRADISTE;BECOV NAD TEPLOU;OSTROV NAD OHRI;VRANOVA LHOTA;PETROVICE U RAKOVNIK;SMIRICE;SUCHDOL U PROSENICKE;MORAVANY U RONOVA;CHVALKOVICE;CHVALKOVICE V CECHAC";
    private static final List<String> LIST_NAZVU = new ArrayList<>();
    private static final String GPS = "N49 33.3690 E014 20.1690;N50 22.7895 E014 44.6632;N49 03.9274 E016 28.4618;N49 08.5790 E014 20.8478;N50 07.3245 E016 11.8987;N48 44.3606 E016 45.3169;N50 18.1108 E012 36.1148;N49 40.1411 E013 23.7164;N50 06.3512 E013 14.7956;N50 22.5719 E015 34.6614;N50 09.9689 E016 27.8094;N49 55.0717 E015 48.6536;N50 14.0811 E013 43.5633;N49 06.0028 E016 01.0550;N49 00.1423 E014 46.1383;N50 04.8127 E014 15.3027;N49 52.3565 E013 30.7633;N49 02.1378 E016 36.7920;N49 21.6956 E015 09.5395;N49 26.4373 E013 22.4042;N50 31.7592 E014 58.2678;N50 05.1268 E012 50.3332;N50 18.0993 E012 56.3424;N49 42.7327 E016 49.6135;N50 04.0582 E013 38.1595;N50 17.9744 E015 52.3650;N49 41.9860 E014 29.6968;N49 50.6261 E015 32.5355;N49 10.8913 E017 05.8799;N50 24.9627 E015 58.9568";
    private static final List<String> LIST_GPS = new ArrayList<>();

    static {
        Arrays.stream(NAZVY.split(";")).forEach(LIST_NAZVU::add);
        Arrays.stream(GPS.split(";")).forEach(LIST_GPS::add);

        NAZVY_POCET = LIST_NAZVU.size();
        GPS_POCET = LIST_GPS.size();
    }

    private static final Random rnd = new Random();

    private ZamekGenerator() {
    }

    public static Stream<Zamek> generujZamky(int pocet) {
        Stream.Builder<Zamek> generovaneZamky = Stream.builder();

        for (int i = 0; i < pocet; i++) {
            generovaneZamky.add(generujZamek());
        }

        return generovaneZamky.build();
    }

    public static Zamek generujZamek() {
        return new Zamek(LIST_NAZVU.get(rnd.nextInt(NAZVY_POCET)), LIST_GPS.get(rnd.nextInt(GPS_POCET)));
    }
}
