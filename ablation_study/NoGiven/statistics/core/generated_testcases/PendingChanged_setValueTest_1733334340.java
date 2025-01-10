
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Consumer;

public class PendingChanged_setValueTest {

    @Test
    public void testSetValue_FirstTime() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.of("test");

        pendingChanged.setValue(value);

        assertTrue(pendingChanged.isValueSet());
        assertTrue(pendingChanged.isPresent());
        assertEquals("test", pendingChanged.get());
    }

    @Test
    public void testSetValue_SecondTime() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.of("test");

        pendingChanged.setValue(value);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            pendingChanged.setValue(value);
        });

        assertEquals("PendingChanged may not be set more than once. Value was already set.", exception.getMessage());
    }

    @Test
    public void testSetValue_EmptyOptional() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.empty();

        pendingChanged.setValue(value);

        assertTrue(pendingChanged.isValueSet());
        assertFalse(pendingChanged.isPresent());
        assertNull(pendingChanged.get());
    }

    @Test
    public void testSetValue_Consumers() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.of("test");
        Consumer<String> ifPresentConsumer = mock(Consumer.class);
        Consumer<Optional<String>> whenSetConsumer = mock(Consumer.class);

        pendingChanged.ifPresent(ifPresentConsumer);
        pendingChanged.whenSet(whenSetConsumer);

        pendingChanged.setValue(value);

        verify(ifPresentConsumer).accept("test");
        verify(whenSetConsumer).accept(value);
    }

    private <T> Consumer<T> mock(Class<Consumer<T>> classToMock) {
        return null; // Mocking logic would go here
    }
}
