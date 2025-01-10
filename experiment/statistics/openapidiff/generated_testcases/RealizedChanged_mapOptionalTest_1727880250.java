
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealizedChanged_mapOptionalTest {

    @Test
    public void testMapOptional_WithValuePresent() {
        // Given
        RealizedChanged<String> realizedChanged = new RealizedChanged<>("test");
        Function<Optional<String>, Optional<Integer>> function = opt -> opt.map(String::length);

        // When
        DeferredChanged<Integer> result = realizedChanged.mapOptional(function);

        // Then
        assertEquals(Optional.of(4), ((RealizedChanged<Integer>) result).value);
    }

    @Test
    public void testMapOptional_WithEmptyValue() {
        // Given
        RealizedChanged<String> realizedChanged = new RealizedChanged<>(Optional.empty());
        Function<Optional<String>, Optional<Integer>> function = opt -> opt.map(String::length);

        // When
        DeferredChanged<Integer> result = realizedChanged.mapOptional(function);

        // Then
        assertEquals(Optional.empty(), ((RealizedChanged<Integer>) result).value);
    }
}
