
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Transformer;
import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_transformedIteratorTest {

    @Test
    void testTransformedIterator_NonNullInputs() {
        Iterator<String> inputIterator = new ArrayIterator<>(new String[]{"a", "b", "c"});
        Transformer<String, String> transformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return input.toUpperCase();
            }
        };

        Iterator<String> transformedIterator = IteratorUtils.transformedIterator(inputIterator, transformer);

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
    void testTransformedIterator_NullIterator() {
        Transformer<String, String> transformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return input.toUpperCase();
            }
        };

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.transformedIterator(null, transformer);
        });
    }

    @Test
    void testTransformedIterator_NullTransformer() {
        Iterator<String> inputIterator = new ArrayIterator<>(new String[]{"a", "b", "c"});

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.transformedIterator(inputIterator, null);
        });
    }
}
