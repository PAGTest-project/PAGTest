
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.TransformerUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IteratorUtils_toStringTest {

    @Test
    public void testToString_EmptyIterator() {
        Iterator<String> emptyIterator = Arrays.asList().iterator();
        String result = IteratorUtils.toString(emptyIterator);
        assertEquals("[]", result);
    }

    @Test
    public void testToString_NonEmptyIterator() {
        Iterator<String> nonEmptyIterator = Arrays.asList("a", "b", "c").iterator();
        String result = IteratorUtils.toString(nonEmptyIterator);
        assertEquals("[a, b, c]", result);
    }
}
