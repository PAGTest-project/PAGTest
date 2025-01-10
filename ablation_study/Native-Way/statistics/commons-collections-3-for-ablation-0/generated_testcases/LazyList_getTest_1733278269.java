
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LazyList_getTest {

    private LazyList<String> lazyListWithFactory;
    private LazyList<String> lazyListWithTransformer;

    @BeforeEach
    public void setUp() {
        Factory<String> factory = () -> "FactoryElement";
        Transformer<Integer, String> transformer = index -> "TransformerElement" + index;

        lazyListWithFactory = new LazyList<>(new ArrayList<>(), factory);
        lazyListWithTransformer = new LazyList<>(new ArrayList<>(), transformer);
    }

    @Test
    public void testGetWithinBoundsWithFactory() {
        lazyListWithFactory.add("InitialElement");
        assertEquals("InitialElement", lazyListWithFactory.get(0));
    }

    @Test
    public void testGetWithinBoundsWithTransformer() {
        lazyListWithTransformer.add("InitialElement");
        assertEquals("InitialElement", lazyListWithTransformer.get(0));
    }

    @Test
    public void testGetOutOfBoundsWithFactory() {
        String element = lazyListWithFactory.get(5);
        assertNotNull(element);
        assertEquals("FactoryElement", element);
        assertEquals(6, lazyListWithFactory.size());
    }

    @Test
    public void testGetOutOfBoundsWithTransformer() {
        String element = lazyListWithTransformer.get(5);
        assertNotNull(element);
        assertEquals("TransformerElement5", element);
        assertEquals(6, lazyListWithTransformer.size());
    }

    @Test
    public void testGetWithNullFactoryAndTransformer() {
        LazyList<String> lazyList = new LazyList<>(new ArrayList<>(), null);
        assertThrows(IllegalStateException.class, () -> lazyList.get(0));
    }
}
