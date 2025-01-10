
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ComparatorChain_setComparatorTest {

    private ComparatorChain<String> comparatorChain;
    private Comparator<String> mockComparator;

    @BeforeEach
    public void setUp() {
        comparatorChain = new ComparatorChain<>();
        mockComparator = mock(Comparator.class);
    }

    @Test
    public void testSetComparator_ForwardOrder() {
        // Given
        comparatorChain.addComparator(mockComparator);

        // When
        comparatorChain.setComparator(0, mockComparator, false);

        // Then
        verify(mockComparator, never()).compare(any(), any());
    }

    @Test
    public void testSetComparator_ReverseOrder() {
        // Given
        comparatorChain.addComparator(mockComparator);

        // When
        comparatorChain.setComparator(0, mockComparator, true);

        // Then
        verify(mockComparator, never()).compare(any(), any());
    }

    @Test
    public void testSetComparator_LockedChain() {
        // Given
        comparatorChain.addComparator(mockComparator);
        comparatorChain.compare("a", "b"); // Lock the chain

        // When & Then
        assertThrows(UnsupportedOperationException.class, () -> {
            comparatorChain.setComparator(0, mockComparator, false);
        });
    }
}
