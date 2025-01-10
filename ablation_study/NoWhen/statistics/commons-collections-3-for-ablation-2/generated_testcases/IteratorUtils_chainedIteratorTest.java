
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_chainedIteratorTest {

    @Test
    public void testChainedIterator() {
        Iterator<Integer> iterator1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(4, 5, 6).iterator();

        Iterator<Integer> chainedIterator = IteratorUtils.chainedIterator(iterator1, iterator2);

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
