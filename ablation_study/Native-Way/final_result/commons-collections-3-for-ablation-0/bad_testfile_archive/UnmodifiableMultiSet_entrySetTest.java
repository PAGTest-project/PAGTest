
package org.apache.commons.collections4.multiset;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UnmodifiableMultiSet_entrySetTest {

    @Test
    public void testEntrySet() {
        // Given
        MultiSet<String> mockMultiSet = mock(MultiSet.class);
        Set<MultiSet.Entry<String>> mockEntrySet = mock(Set.class);
        when(mockMultiSet.entrySet()).thenReturn(mockEntrySet);

        UnmodifiableMultiSet<String> unmodifiableMultiSet = new UnmodifiableMultiSet<>(mockMultiSet);

        // When
        Set<MultiSet.Entry<String>> result = unmodifiableMultiSet.entrySet();

        // Then
        assertTrue(result instanceof UnmodifiableSet);
    }
}
