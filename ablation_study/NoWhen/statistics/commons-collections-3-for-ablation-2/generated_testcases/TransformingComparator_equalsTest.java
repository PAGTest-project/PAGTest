
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformingComparator_equalsTest {

    private Transformer<String, String> transformer;
    private TransformingComparator<String, String> comparator1;
    private TransformingComparator<String, String> comparator2;

    @BeforeEach
    public void setUp() {
        transformer = TransformerUtils.nopTransformer();
        comparator1 = new TransformingComparator<>(transformer);
        comparator2 = new TransformingComparator<>(transformer);
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(comparator1.equals(comparator1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(comparator1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(comparator1.equals("Not a TransformingComparator"));
    }

    @Test
    public void testEqualsSameAttributes() {
        assertTrue(comparator1.equals(comparator2));
    }

    @Test
    public void testEqualsDifferentTransformer() {
        Transformer<String, String> differentTransformer = TransformerUtils.constantTransformer("Different");
        TransformingComparator<String, String> differentComparator = new TransformingComparator<>(differentTransformer);
        assertFalse(comparator1.equals(differentComparator));
    }

    @Test
    public void testEqualsDifferentDecorated() {
        TransformingComparator<String, String> differentComparator = new TransformingComparator<>(transformer, (o1, o2) -> o1.compareTo(o2));
        assertFalse(comparator1.equals(differentComparator));
    }
}
