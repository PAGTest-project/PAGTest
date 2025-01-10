
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UnmodifiableSortedBag_uniqueSetTest {

    @Test
    public void testUniqueSet() {
        // Given
        SortedBag<String> mockBag = mock(SortedBag.class);
        Set<String> mockSet = mock(Set.class);
        when(mockBag.uniqueSet()).thenReturn(mockSet);

        UnmodifiableSortedBag<String> unmodifiableSortedBag = UnmodifiableSortedBag.unmodifiableSortedBag(mockBag);

        // When
        Set<String> result = unmodifiableSortedBag.uniqueSet();

        // Then
        assertTrue(result instanceof UnmodifiableSet);
        verify(mockBag).uniqueSet();
    }
}
