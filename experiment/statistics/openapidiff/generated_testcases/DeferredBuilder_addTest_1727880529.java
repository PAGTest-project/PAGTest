
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Changed;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class DeferredBuilder_addTest {

    private DeferredBuilder<Changed> deferredBuilder;

    @BeforeEach
    public void setUp() {
        deferredBuilder = new DeferredBuilder<>();
    }

    @Test
    public void testAdd() {
        DeferredChanged<Changed> deferredChanged = new RealizedChanged<>(new ChangedReadOnly());
        DeferredBuilder<Changed> result = deferredBuilder.add(deferredChanged);

        // Verify that the deferredChanged was added to the deferredValues list
        List<DeferredChanged<? extends Changed>> deferredValues = deferredBuilder.deferredValues;
        assertEquals(1, deferredValues.size());
        assertSame(deferredChanged, deferredValues.get(0));

        // Verify that the method returns the same DeferredBuilder instance
        assertSame(deferredBuilder, result);
    }
}
