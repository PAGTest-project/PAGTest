
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PendingChanged_mapOptionalTest {

    @Test
    public void testMapOptional_ValueSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("test"));

        Function<Optional<String>, Optional<Integer>> function = opt -> opt.map(String::length);
        DeferredChanged<Integer> result = pendingChanged.mapOptional(function);

        assertTrue(result instanceof RealizedChanged);
        assertEquals(Optional.of(4), ((RealizedChanged<Integer>) result).get());
    }

    @Test
    public void testMapOptional_ValueNotSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();

        Function<Optional<String>, Optional<Integer>> function = opt -> opt.map(String::length);
        DeferredChanged<Integer> result = pendingChanged.mapOptional(function);

        assertTrue(result instanceof PendingChanged);
        assertEquals(1, PendingChanged.deferredCounter.get());

        pendingChanged.setValue(Optional.of("test"));
        assertEquals(1, PendingChanged.resolvedCounter.get());
        assertEquals(Optional.of(4), ((PendingChanged<Integer>) result).get());
    }
}
