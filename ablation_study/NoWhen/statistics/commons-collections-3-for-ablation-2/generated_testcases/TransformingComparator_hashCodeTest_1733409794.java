
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        TransformingComparator<String, String> comparator2 = new TransformingComparator<>(transformer);
        assertEquals(comparator.hashCode(), comparator2.hashCode(), "Hash codes should be equal");
    }

    @Test
    public void testHashCodeWithNullDecorated() {
        TransformingComparator<String, String> comparatorWithNullDecorated = new TransformingComparator<>(transformer, null);
        TransformingComparator<String, String> comparatorWithNonNullDecorated = new TransformingComparator<>(transformer);
        assertEquals(comparatorWithNullDecorated.hashCode(), comparatorWithNonNullDecorated.hashCode(), "Hash codes should be equal");
    }

    @Test
    public void testHashCodeWithNullTransformer() {
        TransformingComparator<String, String> comparatorWithNullTransformer = new TransformingComparator<>(null);
        TransformingComparator<String, String> comparatorWithNonNullTransformer = new TransformingComparator<>(transformer);
        assertEquals(comparatorWithNullTransformer.hashCode(), comparatorWithNonNullTransformer.hashCode(), "Hash codes should be equal");
    }
}
