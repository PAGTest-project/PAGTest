
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PendingChanged_flatMapTest {

    private PendingChanged<String> pendingChanged;
    private Function<Optional<String>, DeferredChanged<Integer>> mockFunction;

    @BeforeEach
    public void setUp() {
        pendingChanged = new PendingChanged<>();
        mockFunction = Mockito.mock(Function.class);
    }

    @Test
    public void testFlatMapWithValueSet() {
        // Given
        pendingChanged.setValue(Optional.of("test"));
        DeferredChanged<Integer> mockDeferredChanged = Mockito.mock(DeferredChanged.class);
        when(mockFunction.apply(any())).thenReturn(mockDeferredChanged);

        // When
        DeferredChanged<Integer> result = pendingChanged.flatMap(mockFunction);

        // Then
        assertEquals(mockDeferredChanged, result);
        verify(mockFunction).apply(Optional.of("test"));
        verify(mockDeferredChanged).whenSet(any());
    }

    @Test
    public void testFlatMapWithoutValueSet() {
        // Given
        DeferredChanged<Integer> mockDeferredChanged = Mockito.mock(DeferredChanged.class);
        when(mockFunction.apply(any())).thenReturn(mockDeferredChanged);

        // When
        DeferredChanged<Integer> result = pendingChanged.flatMap(mockFunction);

        // Then
        assertEquals(PendingChanged.class, result.getClass());
        verify(mockFunction, never()).apply(any());

        // Simulate setting the value later
        pendingChanged.setValue(Optional.of("test"));
        verify(mockFunction).apply(Optional.of("test"));
        verify(mockDeferredChanged).whenSet(any());
    }
}
