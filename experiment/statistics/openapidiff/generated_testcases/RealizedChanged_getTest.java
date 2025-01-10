
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class RealizedChanged_getTest {

    @Test
    public void testGetWithValuePresent() {
        // Given
        RealizedChanged<String> realizedChanged = new RealizedChanged<>("testValue");

        // When
        String result = realizedChanged.get();

        // Then
        assertEquals("testValue", result);
    }

    @Test
    public void testGetWithEmptyValue() {
        // Given
        RealizedChanged<String> realizedChanged = RealizedChanged.empty();

        // When & Then
        assertThrows(NoSuchElementException.class, () -> realizedChanged.get());
    }
}
