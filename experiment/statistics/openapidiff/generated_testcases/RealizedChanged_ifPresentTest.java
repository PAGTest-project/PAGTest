
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class RealizedChanged_ifPresentTest {

    @Test
    public void testIfPresentWithValue() {
        // Given
        RealizedChanged<String> realizedChanged = new RealizedChanged<>("testValue");
        Consumer<String> consumer = mock(Consumer.class);

        // When
        realizedChanged.ifPresent(consumer);

        // Then
        verify(consumer).accept("testValue");
    }

    @Test
    public void testIfPresentWithEmpty() {
        // Given
        RealizedChanged<String> realizedChanged = new RealizedChanged<>(Optional.empty());
        Consumer<String> consumer = mock(Consumer.class);

        // When
        realizedChanged.ifPresent(consumer);

        // Then
        verify(consumer, never()).accept(any());
    }
}
