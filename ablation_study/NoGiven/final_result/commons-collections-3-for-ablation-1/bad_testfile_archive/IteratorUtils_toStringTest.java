
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Iterator;

public class IteratorUtils_toStringTest {

    @Test
    public void testToString_WithNonEmptyIterator() {
        // Given
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();

        // When
        String result = IteratorUtils.toString(iterator, TransformerUtils.stringValueTransformer(),
                ", ", "[", "]");

        // Then
        assertEquals("[a, b, c]", result);
    }

    @Test
    public void testToString_WithEmptyIterator() {
        // Given
        Iterator<String> iterator = Arrays.asList().iterator();

        // When
        String result = IteratorUtils.toString(iterator, TransformerUtils.stringValueTransformer(),
                ", ", "[", "]");

        // Then
        assertEquals("[]", result);
    }
}
