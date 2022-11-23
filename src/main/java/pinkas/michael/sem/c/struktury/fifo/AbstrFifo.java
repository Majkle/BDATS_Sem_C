package pinkas.michael.sem.c.struktury.fifo;

import pinkas.michael.sem.c.struktury.adl.AbstrDoubleList;
import pinkas.michael.sem.c.struktury.adl.IAbstrDoubleList;

import java.util.Iterator;

public class AbstrFifo<T> implements IAbstrFifo<T> {

    private final IAbstrDoubleList<T> abstrDoubleList;

    public AbstrFifo() {
        this.abstrDoubleList = new AbstrDoubleList<>();
    }

    @Override
    public void zrus() {
        abstrDoubleList.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return _jePrazdny();
    }

    private boolean _jePrazdny() {
        return abstrDoubleList.jePrazdny();
    }

    @Override
    public void vloz(T data) throws AbstrFifoException {
        kontrolaDat(data);

        abstrDoubleList.vlozPrvni(data);
    }

    @Override
    public T odeber() throws AbstrFifoException {
        kontrolaPlnostiFronty();

        return abstrDoubleList.odeberPosledni();
    }



    private void kontrolaPlnostiFronty() {
        if (_jePrazdny()) {
            throw new AbstrFifoException("Fronta neobsahuje žádné prvky");
        }
    }

    private void kontrolaDat(T data) {
        if (data == null) {
            throw new AbstrFifoException("Žádná data pro vložení");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return abstrDoubleList.iterator();
    }
}
