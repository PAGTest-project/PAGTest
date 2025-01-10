
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class PendingChanged_setValueTest {

    @Test
    public void testSetValue_FirstTime() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.of("testValue");

        pendingChanged.setValue(value);

        assertTrue(pendingChanged.isValueSet());
        assertTrue(pendingChanged.isPresent());
        assertEquals("testValue", pendingChanged.get());
    }

    @Test
    public void testSetValue_SecondTime() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Optional<String> value = Optional.of("testValue");
        pendingChanged.setValue(value);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            pendingChanged.setValue(value);
        });

        assertEquals("PendingChanged may not be set more than once. Value was already set.", exception.getMessage());
    }
}
