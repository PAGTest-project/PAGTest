
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedList_setTest {

    private List<String> originalList;
    private TransformedList<String> transformedList;

    @BeforeEach
    public void setUp() {
        originalList = new ArrayList<>();
        originalList.add("1");
        originalList.add("2");
        originalList.add("3");

        transformedList = TransformedList.transformingList(originalList, TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
    }

    @Test
    public void testSet_ValidIndex() {
        String result = transformedList.set(1, "4");
        assertNotNull(result);
        assertEquals("2", result);
        assertTrue(transformedList.contains("4"));
    }

    @Test
    public void testSet_InvalidIndex() {
        String result = transformedList.set(5, "5");
        assertNotNull(result);
        assertEquals("5", result);
        assertTrue(transformedList.contains("5"));
    }

    @Test
    public void testSet_NullObject() {
        String result = transformedList.set(2, null);
        assertNotNull(result);
        assertEquals("3", result);
        assertTrue(transformedList.contains(null));
    }
}
