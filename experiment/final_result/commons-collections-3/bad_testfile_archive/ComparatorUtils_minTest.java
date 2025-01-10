
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class ComparatorUtils_minTest {

    @Test
    public void testMinWithNaturalComparator() {
        Comparator<Integer> naturalComparator = ComparatorUtils.naturalComparator();
        Integer result = ComparatorUtils.min(5, 10, naturalComparator);
        assertEquals(5, result);
    }

    @Test
    public void testMinWithNullComparator() {
        Integer result = ComparatorUtils.min(5, 10, null);
        assertEquals(5, result);
    }

    @Test
    public void testMinWithCustomComparator() {
        Comparator<String> customComparator = Comparator.comparingInt(String::length);
        String result = ComparatorUtils.min("short", "longer", customComparator);
        assertEquals("short", result);
    }

    @Test
    public void testMinWithReversedComparator() {
        Comparator<Integer> reversedComparator = ComparatorUtils.reversedComparator(Comparator.naturalOrder());
        Integer result = ComparatorUtils.min(5, 10, reversedComparator);
        assertEquals(10, result);
    }

    @Test
    public void testMinWithNullValues() {
        Comparator<Integer> nullHighComparator = ComparatorUtils.nullHighComparator(Comparator.naturalOrder());
        Integer result = ComparatorUtils.min(null, 10, nullHighComparator);
        assertEquals(10, result);
    }

    @Test
    public void testMinWithEqualValues() {
        Comparator<Integer> naturalComparator = ComparatorUtils.naturalComparator();
        Integer result = ComparatorUtils.min(5, 5, naturalComparator);
        assertEquals(5, result);
    }
}
