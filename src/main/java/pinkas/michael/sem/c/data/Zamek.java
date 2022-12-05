package pinkas.michael.sem.c.data;

public class Zamek {

    private static int pocetZamku = 0;

    private int id;
    private String gps;
    private String nazevPamatky;

    public Zamek(String nazevPamatky, String gps) {
        this.id = ++pocetZamku;
        this.nazevPamatky = nazevPamatky;
        this.gps = gps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazevPamatky() {
        return nazevPamatky;
    }

    public void setNazevPamatky(String nazevPamatky) {
        this.nazevPamatky = nazevPamatky;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Zamek{" +
                "id=" + id +
                ", nazevPamatky='" + nazevPamatky + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }
}
