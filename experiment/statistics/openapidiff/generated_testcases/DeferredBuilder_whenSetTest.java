
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeferredBuilder_whenSetTest {

    private DeferredBuilder<String> deferredBuilder;

    @BeforeEach
    public void setUp() {
        deferredBuilder = new DeferredBuilder<>();
    }

    @Test
    public void testWhenSet() {
        Consumer<Optional<List<Optional<? super String>>>> consumer = opt -> {
            // Dummy consumer for testing
        };

        DeferredBuilder<String> result = deferredBuilder.whenSet(consumer);

        assertEquals(deferredBuilder, result);
    }
}
