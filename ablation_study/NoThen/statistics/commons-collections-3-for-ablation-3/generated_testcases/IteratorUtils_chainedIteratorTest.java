
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_chainedIteratorTest {

    @Test
    public void testChainedIterator() {
        // Given
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();

        // When
        Iterator<Integer> chainedIterator = IteratorUtils.chainedIterator(iterator1, iterator2);

        // Then
        assertTrue(chainedIterator.hasNext());
        assertEquals(1, chainedIterator.next());
        assertEquals(2, chainedIterator.next());
        assertEquals(3, chainedIterator.next());
        assertEquals(4, chainedIterator.next());
        assertEquals(5, chainedIterator.next());
        assertEquals(6, chainedIterator.next());
        assertTrue(!chainedIterator.hasNext());
    }
}
