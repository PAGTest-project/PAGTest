
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeferredBuilder_addAllTest {

    @Test
    public void testAddAll() {
        // Given
        DeferredBuilder<String> builder = new DeferredBuilder<>();
        DeferredChanged<String> deferred1 = new DeferredChanged<>();
        DeferredChanged<String> deferred2 = new DeferredChanged<>();
        List<DeferredChanged<String>> values = Arrays.asList(deferred1, deferred2);

        // When
        builder.addAll(values);

        // Then
        assertEquals(2, builder.deferredValues.size());
        assertTrue(builder.deferredValues.containsAll(values));
    }
}
