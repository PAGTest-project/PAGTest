
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LazyEvaluated_getTest {

    @Test
    public void testGet_ValueNotInitialized() {
        // Given
        Supplier<String> mockSupplier = mock(Supplier.class);
        when(mockSupplier.get()).thenReturn("initializedValue");
        LazyEvaluated<String> lazyEvaluated = new LazyEvaluated<>(mockSupplier);

        // When
        String result = lazyEvaluated.get();

        // Then
        assertEquals("initializedValue", result);
        verify(mockSupplier, times(1)).get();
    }

    @Test
    public void testGet_ValueAlreadyInitialized() {
        // Given
        Supplier<String> mockSupplier = mock(Supplier.class);
        when(mockSupplier.get()).thenReturn("initializedValue");
        LazyEvaluated<String> lazyEvaluated = new LazyEvaluated<>(mockSupplier);
        lazyEvaluated.get(); // Initialize the value

        // When
        String result = lazyEvaluated.get();

        // Then
        assertEquals("initializedValue", result);
        verify(mockSupplier, times(1)).get(); // Ensure supplier.get() is called only once
    }
}
