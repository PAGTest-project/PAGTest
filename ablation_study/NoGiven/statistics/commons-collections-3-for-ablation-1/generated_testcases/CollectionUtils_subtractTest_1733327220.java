
package org.apache.commons.collections4;

import org.apache.commons.collections4.bag.HashBag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtils_subtractTest {

    @Test
    public void testSubtract() {
        // Given
        Iterable<Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        Iterable<Integer> b = Arrays.asList(4, 5, 6, 7);
        Predicate<Integer> p = x -> x % 2 == 0;

        // When
        Collection<Integer> result = CollectionUtils.subtract(a, b, p::test);

        // Then
        assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
