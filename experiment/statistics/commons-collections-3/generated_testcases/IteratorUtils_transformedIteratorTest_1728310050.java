
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_transformedIteratorTest {

    @Test
    public void testTransformedIterator_NonNullInputs() {
        Iterator<Integer> inputIterator = new ArrayIterator<>(new Integer[]{1, 2, 3});
        Transformer<Integer, String> transformer = Object::toString;

        Iterator<String> transformedIterator = IteratorUtils.transformedIterator(inputIterator, transformer);

        assertTrue(transformedIterator.hasNext());
        assertEquals("1", transformedIterator.next());
        assertEquals("2", transformedIterator.next());
        assertEquals("3", transformedIterator.next());
        assertFalse(transformedIterator.hasNext());
        assertThrows(NoSuchElementException.class, transformedIterator::next);
    }

    @Test
    public void testTransformedIterator_NullIterator() {
        Transformer<Integer, String> transformer = Object::toString;

        assertThrows(NullPointerException.class, () -> IteratorUtils.transformedIterator(null, transformer));
    }

    @Test
    public void testTransformedIterator_NullTransformer() {
        Iterator<Integer> inputIterator = new ArrayIterator<>(new Integer[]{1, 2, 3});

        assertThrows(NullPointerException.class, () -> IteratorUtils.transformedIterator(inputIterator, null));
    }
}
