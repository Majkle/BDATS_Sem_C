package pinkas.michael.sem.c.struktury.lifo;

import pinkas.michael.sem.c.struktury.adl.AbstrDoubleList;
import pinkas.michael.sem.c.struktury.adl.IAbstrDoubleList;

import java.util.Iterator;

public class AbstrLifo<T> implements IAbstrLifo<T> {

    private final IAbstrDoubleList<T> abstrDoubleList;

    public AbstrLifo() {
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
    public void vloz(T data) throws AbstrLifoException {
        kontrolaDat(data);

        abstrDoubleList.vlozPrvni(data);
    }

    @Override
    public T odeber() throws AbstrLifoException {
        kontrolaPlnostiZasobniku();

        return abstrDoubleList.odeberPrvni();
    }


    @Override
    public Iterator<T> iterator(){
        return abstrDoubleList.iterator();
    }

    private void kontrolaPlnostiZasobniku() {
        if (_jePrazdny()) {
            throw new AbstrLifoException("Zásobník neobsahuje žádné prvky");
        }
    }

    private void kontrolaDat(T data) {
        if (data == null) {
            throw new AbstrLifoException("Žádná data pro vložení");
        }
    }
}
