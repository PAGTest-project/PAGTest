
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LazyList_subListTest {

    private List<String> list;
    private Factory<String> factory;
    private Transformer<Integer, String> transformer;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        factory = () -> "FactoryElement";
        transformer = index -> "TransformerElement" + index;
    }

    @Test
    public void testSubListWithFactory() {
        LazyList<String> lazyList = new LazyList<>(list, factory);
        list.add("Element1");
        list.add("Element2");
        List<String> subList = lazyList.subList(1, 2);
        assertTrue(subList instanceof LazyList);
        assertEquals("Element2", subList.get(0));
    }

    @Test
    public void testSubListWithTransformer() {
        LazyList<String> lazyList = new LazyList<>(list, transformer);
        list.add("Element1");
        list.add("Element2");
        List<String> subList = lazyList.subList(1, 2);
        assertTrue(subList instanceof LazyList);
        assertEquals("Element2", subList.get(0));
    }

    @Test
    public void testSubListWithNullFactoryAndTransformer() {
        LazyList<String> lazyList = new LazyList<>(list, (Factory<String>) null);
        list.add("Element1");
        list.add("Element2");
        assertThrows(IllegalStateException.class, () -> lazyList.subList(1, 2));
    }
}
