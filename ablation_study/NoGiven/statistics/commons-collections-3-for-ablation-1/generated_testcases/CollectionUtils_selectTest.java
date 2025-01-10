
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtils_selectTest {

    @Test
    public void testSelectWithNonEmptyCollection() {
        Collection<Integer> inputCollection = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i % 2 == 0;
        Collection<Integer> expected = Arrays.asList(2, 4);

        Collection<Integer> result = CollectionUtils.select(inputCollection, predicate::test);

        assertEquals(expected, result);
    }

    @Test
    public void testSelectWithEmptyCollection() {
        Collection<Integer> inputCollection = new ArrayList<>();
        Predicate<Integer> predicate = i -> i % 2 == 0;
        Collection<Integer> expected = new ArrayList<>();

        Collection<Integer> result = CollectionUtils.select(inputCollection, predicate::test);

        assertEquals(expected, result);
    }

    @Test
    public void testSelectWithNullCollection() {
        Collection<Integer> inputCollection = null;
        Predicate<Integer> predicate = i -> i % 2 == 0;
        Collection<Integer> expected = new ArrayList<>();

        Collection<Integer> result = CollectionUtils.select(inputCollection, predicate::test);

        assertEquals(expected, result);
    }
}
