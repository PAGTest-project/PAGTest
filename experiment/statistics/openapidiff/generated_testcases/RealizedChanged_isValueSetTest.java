
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class RealizedChanged_isValueSetTest {

    @Test
    public void testIsValueSet() {
        RealizedChanged<String> realizedChanged = new RealizedChanged<>("testValue");
        assertTrue(realizedChanged.isValueSet());
    }
}
