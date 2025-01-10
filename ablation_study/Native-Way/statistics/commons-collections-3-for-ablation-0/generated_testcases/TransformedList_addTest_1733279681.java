
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedList_addTest {

    private TransformedList<String> transformedList;
    private List<String> originalList;

    @BeforeEach
    public void setUp() {
        originalList = new ArrayList<>();
        transformedList = TransformedList.transformingList(originalList, TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
    }

    @Test
    public void testAdd_TransformObject() {
        transformedList.add(0, "10");
        assertEquals(1, transformedList.size());
        assertTrue(transformedList.contains(10));
    }

    @Test
    public void testAdd_TransformObjectAtNonZeroIndex() {
        transformedList.add(0, "10");
        transformedList.add(1, "20");
        assertEquals(2, transformedList.size());
        assertTrue(transformedList.contains(10));
        assertTrue(transformedList.contains(20));
    }

    @Test
    public void testAdd_TransformObjectAtExistingIndex() {
        transformedList.add(0, "10");
        transformedList.add(0, "20");
        assertEquals(2, transformedList.size());
        assertTrue(transformedList.contains(10));
        assertTrue(transformedList.contains(20));
    }
}
