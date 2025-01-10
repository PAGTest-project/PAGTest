
package org.apache.commons.collections4;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IteratorUtils_toStringTest {

    private Iterator<String> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new ArrayIterator<>(new String[]{"a", "b", "c"});
    }

    @Test
    public void testToStringWithDefaultDelimiter() {
        String result = IteratorUtils.toString(iterator);
        assertEquals("[a, b, c]", result);
    }

    @Test
    public void testToStringWithEmptyIterator() {
        iterator = new ArrayIterator<>(new String[]{});
        String result = IteratorUtils.toString(iterator);
        assertEquals("[]", result);
    }

    @Test
    public void testToStringWithSingleElement() {
        iterator = new ArrayIterator<>(new String[]{"a"});
        String result = IteratorUtils.toString(iterator);
        assertEquals("[a]", result);
    }

    @Test
    public void testToStringWithNullElement() {
        iterator = new ArrayIterator<>(new String[]{"a", null, "c"});
        String result = IteratorUtils.toString(iterator);
        assertEquals("[a, null, c]", result);
    }

    @Test
    public void testToStringWithCustomDelimiter() {
        String result = IteratorUtils.toString(iterator, TransformerUtils.stringValueTransformer(), "|", "{", "}");
        assertEquals("{a|b|c}", result);
    }
}
