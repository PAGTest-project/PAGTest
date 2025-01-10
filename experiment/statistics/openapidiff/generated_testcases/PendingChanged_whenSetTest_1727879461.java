
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class PendingChanged_whenSetTest {

    @Test
    public void testWhenSet_ValueAlreadySet() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("testValue"));
        Consumer<Optional<String>> consumer = Optional::isPresent;

        // When
        pendingChanged.whenSet(consumer);

        // Then
        assertTrue(pendingChanged.isValueSet());
    }

    @Test
    public void testWhenSet_ValueNotSet() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Consumer<Optional<String>> consumer = Optional::isPresent;

        // When
        pendingChanged.whenSet(consumer);

        // Then
        assertEquals(1, pendingChanged.whenSetConsumers.size());
    }
}
