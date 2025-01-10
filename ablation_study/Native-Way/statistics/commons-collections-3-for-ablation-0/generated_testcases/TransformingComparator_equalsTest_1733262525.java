
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformingComparator_equalsTest {

    private Transformer<String, String> transformer;
    private Comparator<String> comparator;
    private TransformingComparator<String, String> transformingComparator;

    @BeforeEach
    public void setUp() {
        transformer = TransformerUtils.nopTransformer();
        comparator = ComparatorUtils.NATURAL_COMPARATOR;
        transformingComparator = new TransformingComparator<>(transformer, comparator);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(transformingComparator.equals(transformingComparator));
    }

    @Test
    public void testEqualsNullObject() {
        assertFalse(transformingComparator.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(transformingComparator.equals("Not a TransformingComparator"));
    }

    @Test
    public void testEqualsSameAttributes() {
        TransformingComparator<String, String> other = new TransformingComparator<>(transformer, comparator);
        assertTrue(transformingComparator.equals(other));
    }

    @Test
    public void testEqualsDifferentTransformer() {
        Transformer<String, String> differentTransformer = TransformerUtils.constantTransformer("Different");
        TransformingComparator<String, String> other = new TransformingComparator<>(differentTransformer, comparator);
        assertFalse(transformingComparator.equals(other));
    }

    @Test
    public void testEqualsDifferentComparator() {
        Comparator<String> differentComparator = Comparator.naturalOrder();
        TransformingComparator<String, String> other = new TransformingComparator<>(transformer, differentComparator);
        assertFalse(transformingComparator.equals(other));
    }
}
