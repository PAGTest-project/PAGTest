
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeferredBuilder_acceptTest {

    private DeferredBuilder<String> deferredBuilder;

    @BeforeEach
    public void setUp() {
        deferredBuilder = new DeferredBuilder<>();
    }

    @Test
    public void testAccept() {
        DeferredChanged<String> deferredChanged = new RealizedChanged<>("testValue");
        deferredBuilder.accept(deferredChanged);

        assertEquals(1, deferredBuilder.build().get().size());
        assertEquals(Optional.of("testValue"), deferredBuilder.build().get().get(0));
    }
}
