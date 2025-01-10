
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeferredBuilder_withTest {

    private DeferredBuilder<Object> deferredBuilder;
    private DeferredChanged<Object> deferredChanged;

    @BeforeEach
    public void setUp() {
        deferredBuilder = new DeferredBuilder<>();
        deferredChanged = new DeferredChanged<>() {
            @Override
            public void whenSet(Consumer<Optional<? super Object>> consumer) {
                // Mock implementation
            }
        };
    }

    @Test
    public void testWith_AddsDeferredChangedToList() {
        // Given
        List<DeferredChanged<? extends Object>> initialDeferredValues = new ArrayList<>(deferredBuilder.deferredValues);

        // When
        DeferredChanged<Object> result = deferredBuilder.with(deferredChanged);

        // Then
        assertEquals(deferredChanged, result);
        assertTrue(deferredBuilder.deferredValues.contains(deferredChanged));
        assertEquals(initialDeferredValues.size() + 1, deferredBuilder.deferredValues.size());
    }
}
