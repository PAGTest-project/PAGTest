
package com.github.davidmoten.rtree.internal;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class Util_replaceTest {

    @Test
    public void testReplace() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> replacements = Arrays.asList("d", "e");
        List<String> expected = Arrays.asList("a", "c", "d", "e");

        List<String> result = Util.replace(list, "b", replacements);

        assertEquals(expected, result);
    }
}
