
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Ord_zipTest {

    @Test
    public void testZipIterator() {
        List<String> elements = List.of("a", "b", "c");
        Iterator<Ord<String>> iterator = Ord.zip(elements.iterator());

        assertTrue(iterator.hasNext());
        assertEquals(new Ord<>(0, "a"), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Ord<>(1, "b"), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Ord<>(2, "c"), iterator.next());
        assertFalse(iterator.hasNext());
    }
}
