
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_intersectTest {

    @Test
    public void testIntersect() {
        List<Integer> list0 = Arrays.asList(1, 2, 3, 4);
        Iterable<Integer> list1 = Arrays.asList(3, 4, 5, 6);

        List<Integer> result = Static.intersect(list0, list1);

        assertEquals(Arrays.asList(3, 4), result);
    }
}
