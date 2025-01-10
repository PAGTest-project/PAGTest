
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
    public void testEqualsSameObject() {
        assertTrue(comparator1.equals(comparator1));
    }

    @Test
    public void testEqualsNullObject() {
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
        TransformingComparator<String, String> comparator3 = new TransformingComparator<>(TransformerUtils.constantTransformer("Different"));
        assertFalse(comparator1.equals(comparator3));
    }

    @Test
    public void testEqualsDifferentDecorated() {
        TransformingComparator<String, String> comparator4 = new TransformingComparator<>(transformer, (o1, o2) -> o1.compareTo(o2));
        assertFalse(comparator1.equals(comparator4));
    }

    @Test
    public void testEqualsHashCodeConsistency() {
        assertTrue(comparator1.equals(comparator2) ? comparator1.hashCode() == comparator2.hashCode() : true);
    }
}
