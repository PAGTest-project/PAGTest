
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_transformEagerTest {

    @Test
    public void testTransformEagerWithEmptyIterable() {
        Iterable<String> emptyIterable = Collections.emptyList();
        Function<String, Integer> mapper = String::length;

        ImmutableList<Integer> result = Static.transformEager(emptyIterable, mapper);

        assertEquals(ImmutableList.of(), result);
    }

    @Test
    public void testTransformEagerWithNonEmptyIterable() {
        Iterable<String> nonEmptyIterable = Arrays.asList("one", "two", "three");
        Function<String, Integer> mapper = String::length;

        ImmutableList<Integer> result = Static.transformEager(nonEmptyIterable, mapper);

        assertEquals(ImmutableList.of(3, 3, 5), result);
    }
}
