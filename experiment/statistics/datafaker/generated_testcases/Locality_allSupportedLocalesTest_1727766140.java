
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Locality_allSupportedLocalesTest {
    private Locality locality;

    @BeforeEach
    public void setUp() {
        locality = new Locality(new net.datafaker.Faker());
    }

    @Test
    public void testAllSupportedLocales() {
        Set<String> fileMasks = new HashSet<>();
        List<String> result = locality.allSupportedLocales(fileMasks);
        assertEquals(Locality.LOCALES, result);
    }
}
