
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class RealizedChanged_toStringTest {

    @Test
    public void testToStringWithValue() {
        RealizedChanged<String> realizedChanged = new RealizedChanged<>("testValue");
        assertEquals("RealizedChanged{value=Optional[testValue]}", realizedChanged.toString());
    }

    @Test
    public void testToStringWithEmptyValue() {
        RealizedChanged<String> realizedChanged = new RealizedChanged<>(Optional.empty());
        assertEquals("RealizedChanged{value=Optional.empty}", realizedChanged.toString());
    }
}
