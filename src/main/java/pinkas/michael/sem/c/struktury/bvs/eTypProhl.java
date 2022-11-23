package pinkas.michael.sem.c.struktury.bvs;

public enum eTypProhl {
    SIRKA("do šířky"),
    HLOUBKA("do hloubky");

    private final String nazev;

    eTypProhl(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }
}
