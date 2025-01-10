
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class PendingChanged_mapOptionalTest {

    private PendingChanged<String> pendingChanged;

    @Mock
    private Function<Optional<String>, Optional<String>> mockFunction;

    @BeforeEach
    public void setUp() {
        pendingChanged = new PendingChanged<>();
    }

    @Test
    public void testMapOptional_ValueSet() {
        // Given
        pendingChanged.setValue(Optional.of("test"));
        when(mockFunction.apply(any())).thenReturn(Optional.of("mapped"));

        // When
        DeferredChanged<String> result = pendingChanged.mapOptional(mockFunction);

        // Then
        assertTrue(result instanceof RealizedChanged);
        assertEquals(Optional.of("mapped"), ((RealizedChanged<String>) result).get());
        verify(mockFunction).apply(Optional.of("test"));
    }

    @Test
    public void testMapOptional_ValueNotSet() {
        // Given
        when(mockFunction.apply(any())).thenReturn(Optional.of("mapped"));

        // When
        DeferredChanged<String> result = pendingChanged.mapOptional(mockFunction);

        // Then
        assertTrue(result instanceof PendingChanged);
        verify(mockFunction, never()).apply(any());

        // Simulate setting the value later
        pendingChanged.setValue(Optional.of("test"));
        verify(mockFunction).apply(Optional.of("test"));
    }
}
