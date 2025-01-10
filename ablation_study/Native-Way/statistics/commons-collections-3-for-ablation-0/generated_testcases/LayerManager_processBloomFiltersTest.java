
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LayerManager_processBloomFiltersTest {

    @Test
    void testProcessBloomFilters_AllTrue() {
        // Given
        LayerManager<BloomFilter> layerManager = new LayerManager<>(
                () -> mock(BloomFilter.class),
                lm -> false,
                deque -> {},
                true
        );
        BloomFilter bf1 = mock(BloomFilter.class);
        BloomFilter bf2 = mock(BloomFilter.class);
        layerManager.filters.add(bf1);
        layerManager.filters.add(bf2);

        Predicate<BloomFilter> predicate = mock(Predicate.class);
        when(predicate.test(bf1)).thenReturn(true);
        when(predicate.test(bf2)).thenReturn(true);

        // When
        boolean result = layerManager.processBloomFilters(predicate);

        // Then
        assertTrue(result);
        verify(predicate, times(1)).test(bf1);
        verify(predicate, times(1)).test(bf2);
    }

    @Test
    void testProcessBloomFilters_OneFalse() {
        // Given
        LayerManager<BloomFilter> layerManager = new LayerManager<>(
                () -> mock(BloomFilter.class),
                lm -> false,
                deque -> {},
                true
        );
        BloomFilter bf1 = mock(BloomFilter.class);
        BloomFilter bf2 = mock(BloomFilter.class);
        layerManager.filters.add(bf1);
        layerManager.filters.add(bf2);

        Predicate<BloomFilter> predicate = mock(Predicate.class);
        when(predicate.test(bf1)).thenReturn(true);
        when(predicate.test(bf2)).thenReturn(false);

        // When
        boolean result = layerManager.processBloomFilters(predicate);

        // Then
        assertFalse(result);
        verify(predicate, times(1)).test(bf1);
        verify(predicate, times(1)).test(bf2);
    }
}
