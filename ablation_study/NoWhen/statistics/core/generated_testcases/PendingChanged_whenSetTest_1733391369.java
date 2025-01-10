
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class PendingChanged_whenSetTest {

    @Test
    public void testWhenSet_ValueNotSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Consumer<Optional<String>> consumer = Optional::isPresent;

        pendingChanged.whenSet(consumer);

        assertTrue(pendingChanged.whenSetConsumers.contains(consumer));
    }

    @Test
    public void testWhenSet_ValueAlreadySet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("test"));
        Consumer<Optional<String>> consumer = opt -> assertEquals("test", opt.orElse(null));

        pendingChanged.whenSet(consumer);
    }
}
