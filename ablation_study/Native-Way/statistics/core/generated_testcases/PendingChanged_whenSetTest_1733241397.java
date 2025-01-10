
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
        Consumer<Optional<String>> consumer = Optional::isPresent;
        pendingChanged.whenSet(consumer);
        assertTrue(pendingChanged.whenSetConsumers.contains(consumer));
    }

    @Test
    public void testWhenSet_ValueAlreadySet() {
        pendingChanged.setValue(Optional.of("test"));
        Consumer<Optional<String>> consumer = opt -> assertEquals("test", opt.orElse(null));
        pendingChanged.whenSet(consumer);
    }
}
