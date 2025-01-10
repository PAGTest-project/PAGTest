
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IterableUtils_containsTest {

    @Test
    public void testContains_ObjectFound() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                return o1.equals(o2);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };
        assertTrue(IterableUtils.contains(iterable, "b", equator));
    }

    @Test
    public void testContains_ObjectNotFound() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                return o1.equals(o2);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };
        assertFalse(IterableUtils.contains(iterable, "d", equator));
    }

    @Test
    public void testContains_NullEquator() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        assertThrows(NullPointerException.class, () -> IterableUtils.contains(iterable, "b", null));
    }

    @Test
    public void testContains_EmptyIterable() {
        Iterable<String> iterable = Collections.emptyList();
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                return o1.equals(o2);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };
        assertFalse(IterableUtils.contains(iterable, "b", equator));
    }
}
