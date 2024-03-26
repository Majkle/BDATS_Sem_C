package pinkas.michael.sem.c;

import java.util.Objects;

public class TestDataKeyValue {

    public TestDataKeyValue(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public final Integer key;
    public final Integer value;

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDataKeyValue testData = (TestDataKeyValue) o;
        return key == testData.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}