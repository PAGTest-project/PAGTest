
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Changed;
import org.openapitools.openapidiff.core.model.ComposedChanged;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeferredLogger_changedToStringTest {

    @Test
    void testChangedToString_ComposedChanged() {
        ComposedChanged composedChanged = mock(ComposedChanged.class);
        when(composedChanged.getClass()).thenReturn(ComposedChanged.class);

        String result = DeferredLogger.changedToString(composedChanged);
        assertEquals("Changed: class org.openapitools.openapidiff.core.model.ComposedChanged (composed) ", result);
    }

    @Test
    void testChangedToString_NonComposedChanged() {
        Changed changed = mock(Changed.class);
        when(changed.getClass()).thenReturn(Changed.class);
        when(changed.isChanged()).thenReturn(true);

        String result = DeferredLogger.changedToString(changed);
        assertEquals("Changed: interface org.openapitools.openapidiff.core.model.Changed true", result);
    }
}
