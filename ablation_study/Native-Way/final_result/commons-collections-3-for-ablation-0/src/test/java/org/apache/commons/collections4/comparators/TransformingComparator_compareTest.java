
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Comparator;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformingComparator_compareTest {

    private Transformer<String, Integer> transformer;
    private Comparator<Integer> decoratedComparator;
    private TransformingComparator<String, Integer> transformingComparator;

    @BeforeEach
    public void setUp() {
        transformer = s -> Integer.parseInt(s);
        decoratedComparator = Comparator.naturalOrder();
        transformingComparator = new TransformingComparator<>(transformer, decoratedComparator);
    }

    @Test
    public void testCompareLessThan() {
        int result = transformingComparator.compare("10", "20");
        assertEquals(-1, result);
    }

    @Test
    public void testCompareGreaterThan() {
        int result = transformingComparator.compare("30", "20");
        assertEquals(1, result);
    }

    @Test
    public void testCompareEqual() {
        int result = transformingComparator.compare("20", "20");
        assertEquals(0, result);
    }
}
