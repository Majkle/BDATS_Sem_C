package pinkas.michael.sem.c.pamatky;

public enum eTypKey {
    NAZEV("název"),
    GPS("gps");

    private final String nazev;

    eTypKey(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
