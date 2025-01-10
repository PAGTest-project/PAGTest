
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class Ord_reverseTest {

    @Test
    public void testReverse() {
        Iterable<Ord<String>> reversed = Ord.reverse(Arrays.asList("a", "b", "c"));
        Iterator<Ord<String>> iterator = reversed.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(Ord.of(2, "c"), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(Ord.of(1, "b"), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(Ord.of(0, "a"), iterator.next());

        assertFalse(iterator.hasNext());
    }
}
