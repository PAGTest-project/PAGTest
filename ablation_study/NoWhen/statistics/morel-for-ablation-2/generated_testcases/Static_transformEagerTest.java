
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_transformEagerTest {

    @Test
    public void testTransformEager_EmptyIterable() {
        Iterable<String> emptyIterable = Collections.emptyList();
        Function<String, Integer> mapper = Integer::parseInt;

        ImmutableList<Integer> result = Static.transformEager(emptyIterable, mapper);

        assertEquals(ImmutableList.of(), result);
    }

    @Test
    public void testTransformEager_NonEmptyIterable() {
        Iterable<String> nonEmptyIterable = Arrays.asList("1", "2", "3");
        Function<String, Integer> mapper = Integer::parseInt;

        ImmutableList<Integer> result = Static.transformEager(nonEmptyIterable, mapper);

        assertEquals(ImmutableList.of(1, 2, 3), result);
    }
}
