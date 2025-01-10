
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.function.IntPredicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexFilter_testTest {

    private IndexFilter indexFilter;
    private IntPredicate mockTracker;
    private IntPredicate mockConsumer;
    private Shape mockShape;

    @BeforeEach
    void setUp() {
        mockShape = mock(Shape.class);
        mockTracker = mock(IntPredicate.class);
        mockConsumer = mock(IntPredicate.class);
        indexFilter = new IndexFilter(mockShape, mockConsumer) {
            @Override
            IntPredicate getTracker() {
                return mockTracker;
            }
        };
        when(mockShape.getNumberOfBits()).thenReturn(10);
    }

    @Test
    void testNumberTooLarge() {
        assertThrows(IndexOutOfBoundsException.class, () -> indexFilter.test(10));
    }

    @Test
    void testNumberNotSeen() {
        when(mockTracker.test(5)).thenReturn(true);
        when(mockConsumer.test(5)).thenReturn(false);
        assertFalse(indexFilter.test(5));
    }

    @Test
    void testNumberSeen() {
        when(mockTracker.test(5)).thenReturn(false);
        assertTrue(indexFilter.test(5));
    }
}
