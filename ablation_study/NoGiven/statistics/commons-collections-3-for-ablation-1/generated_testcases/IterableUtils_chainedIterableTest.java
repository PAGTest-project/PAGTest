
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IterableUtils_chainedIterableTest {

    @Test
    public void testChainedIterable_SingleIterable() {
        Iterable<Integer> iterable1 = Arrays.asList(1, 2, 3);
        Iterable<Integer> result = IterableUtils.chainedIterable(iterable1);
        List<Integer> resultList = IterableUtils.toList(result);
        assertEquals(Arrays.asList(1, 2, 3), resultList);
    }

    @Test
    public void testChainedIterable_MultipleIterables() {
        Iterable<Integer> iterable1 = Arrays.asList(1, 2);
        Iterable<Integer> iterable2 = Arrays.asList(3, 4);
        Iterable<Integer> result = IterableUtils.chainedIterable(iterable1, iterable2);
        List<Integer> resultList = IterableUtils.toList(result);
        assertEquals(Arrays.asList(1, 2, 3, 4), resultList);
    }

    @Test
    public void testChainedIterable_EmptyIterables() {
        Iterable<Integer> iterable1 = Collections.emptyList();
        Iterable<Integer> iterable2 = Collections.emptyList();
        Iterable<Integer> result = IterableUtils.chainedIterable(iterable1, iterable2);
        List<Integer> resultList = IterableUtils.toList(result);
        assertEquals(Collections.emptyList(), resultList);
    }

    @Test
    public void testChainedIterable_NullIterable() {
        Iterable<Integer> iterable1 = null;
        assertThrows(NullPointerException.class, () -> {
            IterableUtils.chainedIterable(iterable1);
        });
    }
}
