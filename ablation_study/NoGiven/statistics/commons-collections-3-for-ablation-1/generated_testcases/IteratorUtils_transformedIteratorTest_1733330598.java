
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Transformer;
import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_transformedIteratorTest {

    @Test
    public void testTransformedIterator() {
        // Given
        Iterator<String> iterator = new ArrayIterator<>(new String[]{"a", "b", "c"});
        Transformer<String, String> transformer = input -> input.toUpperCase();

        // When
        Iterator<String> transformedIterator = IteratorUtils.transformedIterator(iterator, transformer);

        // Then
        assertTrue(transformedIterator.hasNext());
        assertEquals("A", transformedIterator.next());
        assertTrue(transformedIterator.hasNext());
        assertEquals("B", transformedIterator.next());
        assertTrue(transformedIterator.hasNext());
        assertEquals("C", transformedIterator.next());
        assertFalse(transformedIterator.hasNext());
        assertThrows(NoSuchElementException.class, transformedIterator::next);
    }

    @Test
    public void testTransformedIteratorWithNullIterator() {
        // Given
        Iterator<String> iterator = null;
        Transformer<String, String> transformer = input -> input.toUpperCase();

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> IteratorUtils.transformedIterator(iterator, transformer));
        assertEquals("iterator", exception.getMessage());
    }

    @Test
    public void testTransformedIteratorWithNullTransformer() {
        // Given
        Iterator<String> iterator = new ArrayIterator<>(new String[]{"a", "b", "c"});
        Transformer<String, String> transformer = null;

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> IteratorUtils.transformedIterator(iterator, transformer));
        assertEquals("transformer", exception.getMessage());
    }
}
