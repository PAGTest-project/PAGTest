
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformingComparator_compareTest {

    private Transformer<String, String> transformer;
    private Comparator<String> comparator;
    private TransformingComparator<String, String> transformingComparator;

    @BeforeEach
    public void setUp() {
        transformer = TransformerUtils.nopTransformer();
        comparator = Comparator.naturalOrder();
        transformingComparator = new TransformingComparator<>(transformer, comparator);
    }

    @Test
    public void testCompareEqualObjects() {
        String obj1 = "test";
        String obj2 = "test";
        assertEquals(0, transformingComparator.compare(obj1, obj2));
    }

    @Test
    public void testCompareLessThan() {
        String obj1 = "apple";
        String obj2 = "banana";
        assertTrue(transformingComparator.compare(obj1, obj2) < 0);
    }

    @Test
    public void testCompareGreaterThan() {
        String obj1 = "banana";
        String obj2 = "apple";
        assertTrue(transformingComparator.compare(obj1, obj2) > 0);
    }

    @Test
    public void testEquals() {
        final Transformer<String, String> t1 = TransformerUtils.nopTransformer();
        final TransformingComparator<String, String> comp1 = new TransformingComparator<>(t1);
        final TransformingComparator<String, String> comp2 = new TransformingComparator<>(t1, comp1);

        // Checks the contract: equals-hashCode on comp1 and comp2
        assertTrue(comp1.equals(comp2) ? comp1.hashCode() == comp2.hashCode() : true,
                "Contract failed: equals-hashCode");

        // Checks the contract: equals-hashCode on comp1 and comp2
        assertTrue(comp2.equals(comp1) ? comp2.hashCode() == comp1.hashCode() : true,
                "Contract failed: equals-hashCode");
    }
}
