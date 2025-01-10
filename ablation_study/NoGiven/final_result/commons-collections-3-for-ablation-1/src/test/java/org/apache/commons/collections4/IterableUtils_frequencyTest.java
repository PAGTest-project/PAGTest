
package org.apache.commons.collections4;

import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IterableUtils_frequencyTest {

    @Test
    public void testFrequencyWithSet() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        int frequency = IterableUtils.frequency(set, "a");
        assertEquals(1, frequency);
    }

    @Test
    public void testFrequencyWithBag() {
        Bag<String> bag = new HashBag<>(Arrays.asList("a", "a", "b", "c"));
        int frequency = IterableUtils.frequency(bag, "a");
        assertEquals(2, frequency);
    }

    @Test
    public void testFrequencyWithList() {
        Iterable<String> list = Arrays.asList("a", "a", "b", "c");
        int frequency = IterableUtils.frequency(list, "a");
        assertEquals(2, frequency);
    }
}
