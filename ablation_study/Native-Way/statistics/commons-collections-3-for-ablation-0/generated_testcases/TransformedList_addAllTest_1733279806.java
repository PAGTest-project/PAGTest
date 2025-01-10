
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedList_addAllTest {

    private TransformedList<String> transformedList;
    private List<String> originalList;
    private Transformer<String, String> transformer;

    @BeforeEach
    public void setUp() {
        originalList = new ArrayList<>(Arrays.asList("1", "2", "3"));
        transformer = TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER;
        transformedList = TransformedList.transformingList(originalList, transformer);
    }

    @Test
    public void testAddAll_Success() {
        Collection<String> coll = Arrays.asList("4", "5");
        assertTrue(transformedList.addAll(1, coll));
        assertEquals(5, transformedList.size());
        assertEquals(Integer.valueOf(4), transformedList.get(1));
        assertEquals(Integer.valueOf(5), transformedList.get(2));
    }

    @Test
    public void testAddAll_EmptyCollection() {
        Collection<String> coll = new ArrayList<>();
        assertFalse(transformedList.addAll(1, coll));
        assertEquals(3, transformedList.size());
    }

    @Test
    public void testAddAll_NullCollection() {
        assertFalse(transformedList.addAll(1, null));
        assertEquals(3, transformedList.size());
    }

    @Test
    public void testAddAll_TransformFailure() {
        Collection<String> coll = Arrays.asList("invalid");
        assertFalse(transformedList.addAll(1, coll));
        assertEquals(3, transformedList.size());
    }
}
