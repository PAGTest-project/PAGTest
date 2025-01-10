
package org.apache.commons.collections4.comparators;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformingComparator_hashCodeTest {

    private Transformer<String, String> transformer;
    private TransformingComparator<String, String> comparator;

    @BeforeEach
    public void setUp() {
        transformer = TransformerUtils.nopTransformer();
        comparator = new TransformingComparator<>(transformer);
    }

    @Test
    public void testHashCodeWithNonNullDecoratedAndTransformer() {
        TransformingComparator<String, String> comparatorWithDecorated = new TransformingComparator<>(transformer, comparator);
        int expectedHashCode = 17 * 37 + comparator.hashCode();
        expectedHashCode = expectedHashCode * 37 + transformer.hashCode();
        assertEquals(expectedHashCode, comparatorWithDecorated.hashCode());
    }

    @Test
    public void testHashCodeWithNullDecorated() {
        TransformingComparator<String, String> comparatorWithNullDecorated = new TransformingComparator<>(transformer, null);
        int expectedHashCode = 17 * 37 + 0;
        expectedHashCode = expectedHashCode * 37 + transformer.hashCode();
        assertEquals(expectedHashCode, comparatorWithNullDecorated.hashCode());
    }

    @Test
    public void testHashCodeWithNullTransformer() {
        TransformingComparator<String, String> comparatorWithNullTransformer = new TransformingComparator<>(null, comparator);
        int expectedHashCode = 17 * 37 + comparator.hashCode();
        expectedHashCode = expectedHashCode * 37 + 0;
        assertEquals(expectedHashCode, comparatorWithNullTransformer.hashCode());
    }

    @Test
    public void testHashCodeWithNullDecoratedAndTransformer() {
        TransformingComparator<String, String> comparatorWithNulls = new TransformingComparator<>(null, null);
        int expectedHashCode = 17 * 37 + 0;
        expectedHashCode = expectedHashCode * 37 + 0;
        assertEquals(expectedHashCode, comparatorWithNulls.hashCode());
    }
}
