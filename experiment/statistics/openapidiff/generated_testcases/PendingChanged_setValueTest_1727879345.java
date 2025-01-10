
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
        Optional<String> value1 = Optional.of("test1");
        Optional<String> value2 = Optional.of("test2");

        pendingChanged.setValue(value1);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            pendingChanged.setValue(value2);
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
    public void testSetValue_ConsumersInvoked() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.of("test");
        Consumer<String> ifPresentConsumer = mockConsumer();
        Consumer<Optional<String>> whenSetConsumer = mockOptionalConsumer();

        pendingChanged.ifPresent(ifPresentConsumer);
        pendingChanged.whenSet(whenSetConsumer);

        pendingChanged.setValue(value);

        verify(ifPresentConsumer).accept("test");
        verify(whenSetConsumer).accept(value);
    }

    private Consumer<String> mockConsumer() {
        return s -> {};
    }

    private Consumer<Optional<String>> mockOptionalConsumer() {
        return o -> {};
    }
}
