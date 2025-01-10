
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class PendingChanged_ifPresentTest {

    @Test
    public void testIfPresent_ValueSetAndPresent() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("test"));

        Consumer<String> consumer = s -> assertEquals("test", s);
        pendingChanged.ifPresent(consumer);
    }

    @Test
    public void testIfPresent_ValueNotSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();

        Consumer<String> consumer = s -> assertTrue(false, "Consumer should not be called");
        pendingChanged.ifPresent(consumer);

        // Change the access to ifPresentConsumers to a non-private level in PendingChanged class
        // For example, make it protected or package-private
        // assertEquals(1, pendingChanged.ifPresentConsumers.size());
    }

    @Test
    public void testIfPresent_ValueSetButNotPresent() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.empty());

        Consumer<String> consumer = s -> assertTrue(false, "Consumer should not be called");
        pendingChanged.ifPresent(consumer);
    }
}
