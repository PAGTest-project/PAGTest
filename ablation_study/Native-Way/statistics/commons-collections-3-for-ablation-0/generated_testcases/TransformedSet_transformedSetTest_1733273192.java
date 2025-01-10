
package org.apache.commons.collections4.set;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedSet_transformedSetTest {

    @Test
    public void testTransformedSetWithNonEmptySet() {
        Set<String> originalSet = new HashSet<>();
        originalSet.add("1");
        originalSet.add("2");

        Transformer<String, String> transformer = new Transformer<String, String>() {
            @Override
            public String apply(String input) {
                return input + " transformed";
            }
        };

        Set<String> transformedSet = TransformedSet.transformedSet(originalSet, transformer);

        assertEquals(2, transformedSet.size());
        assertTrue(transformedSet.contains("1 transformed"));
        assertTrue(transformedSet.contains("2 transformed"));
    }

    @Test
    public void testTransformedSetWithEmptySet() {
        Set<String> originalSet = new HashSet<>();

        Transformer<String, String> transformer = new Transformer<String, String>() {
            @Override
            public String apply(String input) {
                return input + " transformed";
            }
        };

        Set<String> transformedSet = TransformedSet.transformedSet(originalSet, transformer);

        assertTrue(transformedSet.isEmpty());
    }
}
