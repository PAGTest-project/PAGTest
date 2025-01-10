
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Options_subsetTest {

    @Test
    void testSubsetWithNegativeSize() {
        Options options = new Options(new BaseProviders());
        assertThrows(IllegalArgumentException.class, () -> options.subset(-1, "a", "b", "c"));
    }

    @Test
    void testSubsetWithZeroSize() {
        Options options = new Options(new BaseProviders());
        assertEquals(Collections.emptySet(), options.subset(0, "a", "b", "c"));
    }

    @Test
    void testSubsetWithSizeGreaterThanOptions() {
        Options options = new Options(new BaseProviders());
        Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertEquals(expected, options.subset(5, "a", "b", "c"));
    }

    @Test
    void testSubsetWithValidSize() {
        Options options = new Options(new BaseProviders());
        Set<String> result = options.subset(2, "a", "b", "c");
        assertEquals(2, result.size());
        assertTrue(result.contains("a") || result.contains("b") || result.contains("c"));
    }
}
