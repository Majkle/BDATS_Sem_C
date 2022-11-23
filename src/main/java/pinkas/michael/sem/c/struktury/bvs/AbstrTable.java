package pinkas.michael.sem.c.struktury.bvs;

import pinkas.michael.sem.c.struktury.fifo.AbstrFifo;
import pinkas.michael.sem.c.struktury.lifo.AbstrLifo;
import pinkas.michael.sem.c.util.VypisStromu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V> {

    Uzel koren;

    @Override
    public void zrus() {
        koren = null;
    }

    @Override
    public boolean jePrazdny() {
        return _jePrazdny();
    }

    @Override
    public V najdi(K key) {
        if (kontrolaHledany(key)) {
            return null;
        }

        Uzel hledany = najdiUzel(koren, key);

        if (hledany == null) {
            return null;
        }

        return hledany.value;
    }

    @Override
    public void vloz(K key, V value) {
        kontrolaVkladany(key, value);

        if (najdiUzel(koren, key) == null) {
            koren = vlozUzel(koren, key, value);
        }
    }

    @Override
    public V odeber(K key) {
        if (kontrolaOdebirany(key)) {
            return null;
        }

        Uzel odebirany = najdiUzel(koren, key);

        if (odebirany == null) {
            return null;
        }

        V value = odebirany.value;

        koren = odeberUzel(koren, key);
        return value;
    }

    private Uzel odeberUzel(Uzel uzel, K key) {
        if (uzel == null) {
            return null;
        }

        int porovnani = key.compareTo(uzel.key);

        if (porovnani < 0) {
            uzel.leva = odeberUzel(uzel.leva, key);
        } else if (porovnani > 0) {
            uzel.prava = odeberUzel(uzel.prava, key);
        } else {
            if (uzel.leva == null) {
                return uzel.prava;
            } else if (uzel.prava == null) {
                return uzel.leva;
            } else {
                Uzel nejvetsi = najdiNejvetsiListZVetve(uzel.leva);
                uzel.key = nejvetsi.key;
                uzel.value = nejvetsi.value;

                uzel.leva = odeberUzel(uzel.leva, uzel.key);
            }
        }

        return uzel;
    }

    private Uzel najdiNejvetsiListZVetve(Uzel uzel) {
        if (uzel.prava != null) {
            return najdiNejvetsiListZVetve(uzel.prava);
        }

        return uzel;
    }

    @Override
    public Iterator<V> vytvorIterator(eTypProhl typ) {
        Iterator<V> it;

        switch (typ) {
            case SIRKA -> it = iteratorDoSirky();
            case HLOUBKA -> it = iteratorDoHloubky();
            default ->
                    throw new AbstrTableException("Nepodporovaný druh iterátoru");
        }

        return it;
    }

    private Iterator<V> iteratorDoHloubky() {

        AbstrLifo<Uzel> zasobnik = new AbstrLifo<>();

        {
            if (!_jePrazdny()) {
                Uzel u = koren;
                while (u != null) {
                    zasobnik.vloz(u);
                    u = u.leva;
                }
            }
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !zasobnik.jePrazdny();
            }

            @Override
            public V next() {
                if (hasNext()) {
                    Uzel dalsi = zasobnik.odeber();

                    Uzel u = dalsi.prava;
                    if (u != null) {
                        while (u != null) {
                            zasobnik.vloz(u);
                            u = u.leva;
                        }
                    }

                    return dalsi.value;
                } else {
                    throw new NoSuchElementException("Žádný další klíč");
                }
            }
        };
    }

    private Iterator<V> iteratorDoSirky() {
        return new Iterator<>() {
            final AbstrFifo<Uzel> fronta = new AbstrFifo<>();

            {
                if (!_jePrazdny()) {
                    fronta.vloz(koren);
                }
            }

            @Override
            public boolean hasNext() {
                return !fronta.jePrazdny();
            }

            @Override
            public V next() {
                if (hasNext()) {
                    Uzel dalsi = fronta.odeber();

                    if (dalsi.leva != null) {
                        fronta.vloz(dalsi.leva);
                    }

                    if (dalsi.prava != null) {
                        fronta.vloz(dalsi.prava);
                    }

                    return dalsi.value;
                } else {
                    throw new NoSuchElementException("Žádný další klíč");
                }
            }
        };
    }

    private boolean _jePrazdny() {
        return koren == null;
    }

    private Uzel najdiUzel(Uzel uzel, K key) {
        if (uzel == null) {
            return null;
        }

        int porovnani = key.compareTo(uzel.key);

        if (porovnani < 0) {
            return najdiUzel(uzel.leva, key);
        } else if (porovnani > 0) {
            return najdiUzel(uzel.prava, key);
        } else {
            return uzel;
        }
    }

    private Uzel vlozUzel(Uzel uzel, K key, V value) {
        if (uzel == null) {
            return new Uzel(key, value);
        }

        if (key.compareTo(uzel.key) < 0) {
            uzel.leva = vlozUzel(uzel.leva, key, value);
        } else {
            uzel.prava = vlozUzel(uzel.prava, key, value);
        }

        return uzel;
    }

    private boolean kontrolaOdebirany(K key) {
        return key == null;
    }

    private boolean kontrolaHledany(K key) {
        return key == null;
    }

    private void kontrolaVkladany(K key, V value) {
        if (key == null) {
            throw new AbstrTableException("Žádný klíč pro vložení");
        }
        if (value == null) {
            throw new AbstrTableException("Žádná hodnota pro vložení");
        }
    }

    public void vypisStromu() {
        VypisStromu.print(koren);
    }

    public class Uzel implements VypisStromu.PrintableNode {
        Uzel leva;
        Uzel prava;
        K key;
        V value;

        public Uzel(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public VypisStromu.PrintableNode getLeft() {
            return leva;
        }

        @Override
        public VypisStromu.PrintableNode getRight() {
            return prava;
        }

        @Override
        public String getText() {
            return String.format("%s", key);
        }
    }
}
