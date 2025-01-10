
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_intersectTest {

    @Test
    public void testIntersect_NoCommonElements() {
        List<Integer> list0 = Arrays.asList(1, 2, 3);
        Iterable<Integer> list1 = Arrays.asList(4, 5, 6);

        List<Integer> result = Static.intersect(list0, list1);

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testIntersect_WithCommonElements() {
        List<Integer> list0 = Arrays.asList(1, 2, 3);
        Iterable<Integer> list1 = Arrays.asList(3, 4, 5);

        List<Integer> result = Static.intersect(list0, list1);

        assertEquals(ImmutableList.of(3), result);
    }

    @Test
    public void testIntersect_AllCommonElements() {
        List<Integer> list0 = Arrays.asList(1, 2, 3);
        Iterable<Integer> list1 = Arrays.asList(1, 2, 3);

        List<Integer> result = Static.intersect(list0, list1);

        assertEquals(ImmutableList.of(1, 2, 3), result);
    }
}
