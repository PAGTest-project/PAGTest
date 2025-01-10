
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_getIteratorTest {

    @Test
    public void testGetIterator() {
        // Given
        Object[] array = {1, 2, 3};
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        Iterator<?> iteratorFromArray = IteratorUtils.getIterator(array);
        Iterator<?> iteratorFromList = IteratorUtils.getIterator(list);
        Iterator<?> iteratorFromMap = IteratorUtils.getIterator(map);
        Iterator<?> iteratorFromNull = IteratorUtils.getIterator(null);

        // Then
        assertTrue(iteratorFromArray instanceof ArrayIterator);
        assertTrue(iteratorFromList instanceof Iterator);
        assertTrue(iteratorFromMap instanceof Iterator);
        assertTrue(iteratorFromNull instanceof EmptyIterator);

        assertEquals(3, Collections.list(new IteratorEnumeration(iteratorFromArray)).size());
        assertEquals(3, Collections.list(new IteratorEnumeration(iteratorFromList)).size());
        assertEquals(2, Collections.list(new IteratorEnumeration(iteratorFromMap)).size());
        assertEquals(0, Collections.list(new IteratorEnumeration(iteratorFromNull)).size());
    }
}
