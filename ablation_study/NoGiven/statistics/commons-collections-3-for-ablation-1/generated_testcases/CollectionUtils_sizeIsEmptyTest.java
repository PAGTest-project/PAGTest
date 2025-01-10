
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Iterator;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_sizeIsEmptyTest {

    @Test
    void testSizeIsEmpty() {
        // Given
        Object nullObject = null;
        Collection<?> emptyCollection = Collections.emptyList();
        Iterable<?> emptyIterable = Collections.emptyList();
        Map<?, ?> emptyMap = Collections.emptyMap();
        Object[] emptyArray = new Object[0];
        Iterator<?> emptyIterator = Collections.emptyList().iterator();
        Enumeration<?> emptyEnumeration = Collections.emptyEnumeration();
        Object nonEmptyArray = new Object[]{new Object()};
        Object invalidObject = new Object();

        // When and Then
        assertTrue(CollectionUtils.sizeIsEmpty(nullObject));
        assertTrue(CollectionUtils.sizeIsEmpty(emptyCollection));
        assertTrue(CollectionUtils.sizeIsEmpty(emptyIterable));
        assertTrue(CollectionUtils.sizeIsEmpty(emptyMap));
        assertTrue(CollectionUtils.sizeIsEmpty(emptyArray));
        assertTrue(CollectionUtils.sizeIsEmpty(emptyIterator));
        assertTrue(CollectionUtils.sizeIsEmpty(emptyEnumeration));
        assertFalse(CollectionUtils.sizeIsEmpty(nonEmptyArray));
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.sizeIsEmpty(invalidObject));
    }
}
