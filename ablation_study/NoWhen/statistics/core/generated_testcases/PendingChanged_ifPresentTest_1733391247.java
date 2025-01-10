
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Consumer;

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
        assertFalse(pendingChanged.isPresent());
        assertEquals(0, result.length());
        assertEquals(1, pendingChanged.ifPresentConsumers.size());
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
        assertFalse(pendingChanged.isPresent());
        assertEquals(0, result.length());
    }
}
