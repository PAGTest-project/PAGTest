
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PendingChanged_toStringTest {

    @Test
    public void testToStringWithValueSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        pendingChanged.setValue(Optional.of("testValue"));
        String expected = "PendingChanged{value=testValue, valueSet=true, ifPresentConsumers.size=0, whenSetConsumers.size=0}";
        assertEquals(expected, pendingChanged.toString());
    }

    @Test
    public void testToStringWithNoValueSet() {
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        String expected = "PendingChanged{value=null, valueSet=false, ifPresentConsumers.size=0, whenSetConsumers.size=0}";
        assertEquals(expected, pendingChanged.toString());
    }
}
