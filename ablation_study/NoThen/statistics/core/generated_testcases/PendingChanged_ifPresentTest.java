
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class PendingChanged_ifPresentTest {

    @Test
    public void testIfPresent_ValueSetAndPresent() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("testValue"));
        StringBuilder result = new StringBuilder();
        Consumer<String> consumer = result::append;

        // When
        pendingChanged.ifPresent(consumer);

        // Then
        assertTrue(pendingChanged.isPresent());
        assertEquals("testValue", result.toString());
    }

    @Test
    public void testIfPresent_ValueNotSet() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        StringBuilder result = new StringBuilder();
        Consumer<String> consumer = result::append;

        // When
        pendingChanged.ifPresent(consumer);

        // Then
        assertEquals(1, pendingChanged.ifPresentConsumers.size());
        assertEquals("", result.toString());
    }

    @Test
    public void testIfPresent_ValueSetButNotPresent() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.empty());
        StringBuilder result = new StringBuilder();
        Consumer<String> consumer = result::append;

        // When
        pendingChanged.ifPresent(consumer);

        // Then
        assertTrue(pendingChanged.isValueSet());
        assertEquals("", result.toString());
    }
}
