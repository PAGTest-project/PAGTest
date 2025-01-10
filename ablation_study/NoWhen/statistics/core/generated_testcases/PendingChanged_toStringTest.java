
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PendingChanged_toStringTest {

    @Test
    public void testToString() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("testValue"));
        pendingChanged.ifPresent(value -> {});
        pendingChanged.whenSet(value -> {});

        String expected = "PendingChanged{value=testValue, valueSet=true, ifPresentConsumers.size=1, whenSetConsumers.size=1}";
        assertEquals(expected, pendingChanged.toString());
    }
}
