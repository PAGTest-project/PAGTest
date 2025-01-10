
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PendingChanged_whenSetTest {

    private PendingChanged<String> pendingChanged;

    @BeforeEach
    public void setUp() {
        pendingChanged = new PendingChanged<>();
    }

    @Test
    public void testWhenSet_ValueNotSet() {
        Consumer<Optional<String>> consumer = value -> assertTrue(value.isEmpty());
        pendingChanged.whenSet(consumer);
        // Assuming there is a method to get the list of consumers
        assertEquals(1, pendingChanged.getWhenSetConsumers().size());
    }

    @Test
    public void testWhenSet_ValueAlreadySet() {
        pendingChanged.setValue(Optional.of("test"));
        Consumer<Optional<String>> consumer = value -> assertEquals("test", value.get());
        pendingChanged.whenSet(consumer);
    }
}
