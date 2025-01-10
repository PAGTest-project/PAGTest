
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class IterableUtils_filteredIterableTest {

    @Test
    public void testFilteredIterable_NonNullIterableAndPredicate() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = n -> n % 2 == 0;

        Iterable<Integer> result = IterableUtils.filteredIterable(iterable, new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer n) {
                return predicate.test(n);
            }
        });
        List<Integer> resultList = IterableUtils.toList(result);

        assertEquals(Arrays.asList(2, 4), resultList);
    }

    @Test
    public void testFilteredIterable_NullIterable() {
        Iterable<Integer> iterable = null;
        Predicate<Integer> predicate = n -> n % 2 == 0;

        assertThrows(NullPointerException.class, () -> {
            IterableUtils.filteredIterable(iterable, new Predicate<Integer>() {
                @Override
                public boolean evaluate(Integer n) {
                    return predicate.test(n);
                }
            });
        });
    }

    @Test
    public void testFilteredIterable_NullPredicate() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = null;

        assertThrows(NullPointerException.class, () -> {
            IterableUtils.filteredIterable(iterable, new Predicate<Integer>() {
                @Override
                public boolean evaluate(Integer n) {
                    return predicate.test(n);
                }
            });
        });
    }
}
