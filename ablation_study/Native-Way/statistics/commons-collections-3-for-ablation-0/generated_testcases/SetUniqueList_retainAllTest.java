
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_retainAllTest {

    private SetUniqueList<String> setUniqueList;

    @BeforeEach
    public void setUp() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        setUniqueList = SetUniqueList.setUniqueList(list);
    }

    @Test
    public void testRetainAllWithMatchingElements() {
        Collection<String> coll = new HashSet<>(Arrays.asList("A", "B"));
        boolean result = setUniqueList.retainAll(coll);
        assertTrue(result);
        assertEquals(2, setUniqueList.size());
        assertTrue(setUniqueList.contains("A"));
        assertTrue(setUniqueList.contains("B"));
    }

    @Test
    public void testRetainAllWithNoMatchingElements() {
        Collection<String> coll = new HashSet<>(Arrays.asList("D", "E"));
        boolean result = setUniqueList.retainAll(coll);
        assertTrue(result);
        assertTrue(setUniqueList.isEmpty());
    }

    @Test
    public void testRetainAllWithAllElementsMatching() {
        Collection<String> coll = new HashSet<>(Arrays.asList("A", "B", "C"));
        boolean result = setUniqueList.retainAll(coll);
        assertFalse(result);
        assertEquals(3, setUniqueList.size());
        assertTrue(setUniqueList.contains("A"));
        assertTrue(setUniqueList.contains("B"));
        assertTrue(setUniqueList.contains("C"));
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        Collection<String> coll = new HashSet<>();
        boolean result = setUniqueList.retainAll(coll);
        assertTrue(result);
        assertTrue(setUniqueList.isEmpty());
    }

    @Test
    public void testRetainAllWithNullCollection() {
        assertThrows(NullPointerException.class, () -> setUniqueList.retainAll(null));
    }
}
