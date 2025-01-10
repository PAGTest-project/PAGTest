
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PendingChanged_ifPresentTest {

    private PendingChanged<String> pendingChanged;

    @BeforeEach
    public void setUp() {
        pendingChanged = new PendingChanged<>();
    }

    @Test
    public void testIfPresent_ValueSetAndPresent() {
        pendingChanged.setValue(Optional.of("test"));
        Consumer<String> consumer = value -> assertEquals("test", value);
        pendingChanged.ifPresent(consumer);
    }

    @Test
    public void testIfPresent_ValueNotSet() {
        Consumer<String> consumer = value -> assertTrue(false, "Should not be called");
        pendingChanged.ifPresent(consumer);
        // Remove the direct access to private field
    }

    @Test
    public void testIfPresent_ValueSetButNotPresent() {
        pendingChanged.setValue(Optional.empty());
        Consumer<String> consumer = value -> assertTrue(false, "Should not be called");
        pendingChanged.ifPresent(consumer);
    }
}
