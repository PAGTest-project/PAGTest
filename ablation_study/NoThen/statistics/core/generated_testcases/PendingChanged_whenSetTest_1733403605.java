
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class PendingChanged_whenSetTest {

    @Test
    public void testWhenSet_ValueAlreadySet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("test"));

        Consumer<Optional<String>> consumer = Optional::isPresent;
        pendingChanged.whenSet(consumer);

        assertTrue(consumer.toString().contains("true"));
    }

    @Test
    public void testWhenSet_ValueNotSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();

        Consumer<Optional<String>> consumer = Optional::isPresent;
        pendingChanged.whenSet(consumer);

        pendingChanged.setValue(Optional.of("test"));

        assertTrue(consumer.toString().contains("true"));
    }
}
