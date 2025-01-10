
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

class PendingChanged_mapOptionalTest {

    @Test
    void testMapOptional_ValueSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("test"));

        Function<Optional<String>, Optional<Integer>> function = opt -> opt.map(String::length);
        DeferredChanged<Integer> result = pendingChanged.mapOptional(function);

        assertTrue(result instanceof RealizedChanged);
        assertEquals(Optional.of(4), ((RealizedChanged<Integer>) result).get());
    }

    @Test
    void testMapOptional_ValueNotSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();

        Function<Optional<String>, Optional<Integer>> function = opt -> opt.map(String::length);
        DeferredChanged<Integer> result = pendingChanged.mapOptional(function);

        assertTrue(result instanceof PendingChanged);
        assertFalse(((PendingChanged<Integer>) result).isValueSet());
    }
}
