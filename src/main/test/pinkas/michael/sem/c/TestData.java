package pinkas.michael.sem.c;

import java.util.Objects;

public class TestData implements Comparable<TestData> {

    public TestData(int data) {
        this.data = data;
    }

    public final int data;

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestData testData = (TestData) o;
        return data == testData.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public int compareTo(TestData o) {
        return Integer.compare(data, o.data);
    }
}