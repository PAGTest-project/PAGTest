
package com.github.davidmoten.rtree.internal;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class Util_addTest {

    @Test
    public void testAddToListWithEmptyList() {
        List<String> list = Collections.emptyList();
        String element = "element";
        List<String> result = Util.add(list, element);
        assertEquals(Arrays.asList("element"), result);
    }

    @Test
    public void testAddToListWithNonEmptyList() {
        List<String> list = Arrays.asList("one", "two");
        String element = "three";
        List<String> result = Util.add(list, element);
        assertEquals(Arrays.asList("one", "two", "three"), result);
    }

    @Test
    public void testAddToListWithNullElement() {
        List<String> list = Arrays.asList("one", "two");
        String element = null;
        List<String> result = Util.add(list, element);
        assertEquals(Arrays.asList("one", "two", null), result);
    }

    @Test
    public void testAddToListWithNullList() {
        List<String> list = null;
        String element = "element";
        List<String> result = Util.add(list, element);
        assertEquals(Arrays.asList("element"), result);
    }
}
