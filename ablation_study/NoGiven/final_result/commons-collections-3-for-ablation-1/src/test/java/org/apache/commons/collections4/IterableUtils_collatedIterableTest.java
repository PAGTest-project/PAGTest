
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IterableUtils_collatedIterableTest {

    @Test
    public void testCollatedIterable() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Iterable<Integer> a = Arrays.asList(1, 3, 5);
        Iterable<Integer> b = Arrays.asList(2, 4, 6);

        Iterable<Integer> result = IterableUtils.collatedIterable(comparator, a, b);
        List<Integer> resultList = IterableUtils.toList(result);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), resultList);
    }
}
