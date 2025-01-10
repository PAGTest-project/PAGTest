
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.IntPredicate;

public class SimpleBloomFilter_processIndicesTest {
    private SimpleBloomFilter filter;

    @BeforeEach
    public void setUp() {
        filter = new SimpleBloomFilter(Shape.fromKM(1, 100));
    }

    @Test
    public void testProcessIndicesWithValidConsumer() {
        IntPredicate consumer = idx -> idx >= 0 && idx < 100;
        assertTrue(filter.processIndices(consumer));
    }

    @Test
    public void testProcessIndicesWithInvalidConsumer() {
        IntPredicate consumer = idx -> idx < 0 || idx >= 100;
        assertFalse(filter.processIndices(consumer));
    }

    @Test
    public void testProcessIndicesWithNullConsumer() {
        assertThrows(NullPointerException.class, () -> filter.processIndices(null));
    }
}
