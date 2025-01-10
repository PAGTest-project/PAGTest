
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class RealizedChanged_isPresentTest {

    @Test
    void testIsPresent_WithValue() {
        RealizedChanged<String> changed = new RealizedChanged<>("test");
        assertTrue(changed.isPresent());
    }

    @Test
    void testIsPresent_WithoutValue() {
        RealizedChanged<String> changed = new RealizedChanged<>(Optional.empty());
        assertFalse(changed.isPresent());
    }
}
