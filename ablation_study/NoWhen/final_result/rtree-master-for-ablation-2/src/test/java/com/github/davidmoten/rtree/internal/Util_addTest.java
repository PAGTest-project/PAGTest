
package com.github.davidmoten.rtree.internal;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class Util_addTest {

    @Test
    public void testAdd() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Integer element = 4;
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);

        List<Integer> result = Util.add(list, element);

        assertEquals(expected, result);
    }
}
