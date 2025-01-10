
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class IterableUtils_isEmptyTest {

    @Test
    public void testIsEmpty_Collection() {
        Collection<Object> emptyCollection = Collections.emptyList();
        assertTrue(IterableUtils.isEmpty(emptyCollection));

        Collection<Object> nonEmptyCollection = List.of("element");
        assertFalse(IterableUtils.isEmpty(nonEmptyCollection));
    }

    @Test
    public void testIsEmpty_Iterable() {
        Iterable<Object> emptyIterable = mock(Iterable.class);
        Iterator<Object> emptyIterator = Collections.emptyIterator();
        when(emptyIterable.iterator()).thenReturn(emptyIterator);
        assertTrue(IterableUtils.isEmpty(emptyIterable));

        Iterable<Object> nonEmptyIterable = mock(Iterable.class);
        Iterator<Object> nonEmptyIterator = List.of("element").iterator();
        when(nonEmptyIterable.iterator()).thenReturn(nonEmptyIterator);
        assertFalse(IterableUtils.isEmpty(nonEmptyIterable));
    }
}
