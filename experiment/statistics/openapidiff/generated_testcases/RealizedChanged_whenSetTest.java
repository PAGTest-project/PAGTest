
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealizedChanged_whenSetTest {

    @Test
    public void testWhenSetWithValue() {
        RealizedChanged<String> realizedChanged = new RealizedChanged<>("testValue");
        Consumer<Optional<String>> consumer = optional -> assertEquals("testValue", optional.orElse(null));
        realizedChanged.whenSet(consumer);
    }

    @Test
    public void testWhenSetWithEmpty() {
        RealizedChanged<String> realizedChanged = RealizedChanged.empty();
        Consumer<Optional<String>> consumer = optional -> assertEquals(null, optional.orElse(null));
        realizedChanged.whenSet(consumer);
    }
}
