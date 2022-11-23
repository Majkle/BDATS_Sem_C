package pinkas.michael.sem.c.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public final class Soubor {

    private Soubor() {
    }

    public static <T> void uloz(Path nazevSouboru, Stream<T> objekty, Function<T, String> zpracovaniRadku) throws IOException {
        Files.write(nazevSouboru, (Iterable<String>) objekty.map(zpracovaniRadku)::iterator);
    }

    public static <T> Stream<T> nacti(Path nazevSouboru, Function<String, T> zpracovaniRadku) throws IOException {
        return Files.lines(nazevSouboru).map(zpracovaniRadku);
    }

}
