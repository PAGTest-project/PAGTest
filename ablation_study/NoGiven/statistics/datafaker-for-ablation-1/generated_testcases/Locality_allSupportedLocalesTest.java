
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import net.datafaker.Faker;

public class Locality_allSupportedLocalesTest {
    private Locality locality;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new Faker());
    }

    @Test
    public void testAllSupportedLocalesWithFileMasks() {
        Set<String> fileMasks = new HashSet<>();
        List<String> expectedLocales = locality.allSupportedLocales();
        List<String> actualLocales = locality.allSupportedLocales(fileMasks);
        assertEquals(expectedLocales, actualLocales);
    }

    @Test
    public void testAllSupportedLocalesWithoutFileMasks() {
        List<String> expectedLocales = locality.allSupportedLocales();
        List<String> actualLocales = locality.allSupportedLocales(null);
        assertEquals(expectedLocales, actualLocales);
    }
}
