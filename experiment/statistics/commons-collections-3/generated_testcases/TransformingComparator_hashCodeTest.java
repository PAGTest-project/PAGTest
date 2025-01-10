
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
    public void testHashCode() {
        int expectedHashCode = 17 * 37 + transformer.hashCode();
        assertEquals(expectedHashCode, comparator.hashCode());
    }
}
