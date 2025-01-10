
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import net.datafaker.service.RandomService;

public class Options_subsetTest {

    private BaseProviders createBaseProviders() {
        return new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        };
    }

    @Test
    public void testSubset_NegativeSize() {
        Options options = new Options(createBaseProviders());
        assertThrows(IllegalArgumentException.class, () -> options.subset(-1, "a", "b", "c"));
    }

    @Test
    public void testSubset_ZeroSize() {
        Options options = new Options(createBaseProviders());
        assertEquals(Collections.emptySet(), options.subset(0, "a", "b", "c"));
    }

    @Test
    public void testSubset_SizeGreaterThanOptions() {
        Options options = new Options(createBaseProviders());
        Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertEquals(expected, options.subset(5, "a", "b", "c"));
    }

    @Test
    public void testSubset_ValidSize() {
        Options options = new Options(createBaseProviders());
        Set<String> result = options.subset(2, "a", "b", "c");
        assertEquals(2, result.size());
        assertTrue(result.contains("a") || result.contains("b") || result.contains("c"));
    }
}
