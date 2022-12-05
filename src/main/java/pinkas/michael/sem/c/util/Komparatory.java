package pinkas.michael.sem.c.util;

import pinkas.michael.sem.c.data.Zamek;

import java.util.Comparator;

public class Komparatory {
    private Komparatory() {
    }

    private static double aktualniLat = 0;
    private static double aktualniLon = 0;

    public static final Comparator<Zamek> komparatorGPS = (z1, z2) -> {
        double z1Lat = Double.parseDouble(z1.getGps().substring(4, 11));
        double z1Lon = Double.parseDouble(z1.getGps().substring(17, 24));

        double z2Lat = Double.parseDouble(z2.getGps().substring(4, 11));
        double z2Lon = Double.parseDouble(z2.getGps().substring(17, 24));

        Double z1Vzdalenost = vzdalenostMezi2GPS(z1Lat, z1Lon, aktualniLat, aktualniLon);
        Double z2Vzdalenost = vzdalenostMezi2GPS(z2Lat, z2Lon, aktualniLat, aktualniLon);

        return z2Vzdalenost.compareTo(z1Vzdalenost);
    };

    private static double vzdalenostMezi2GPS(double lon1, double lat1, double lon2, double lat2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        return dist;
    }

    public static double getAktualniLat() {
        return aktualniLat;
    }

    public static void setAktualniLat(double aktualniLat) {
        Komparatory.aktualniLat = aktualniLat;
    }

    public static double getAktualniLon() {
        return aktualniLon;
    }

    public static void setAktualniLon(double aktualniLon) {
        Komparatory.aktualniLon = aktualniLon;
    }
}
