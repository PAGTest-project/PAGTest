
package org.openapitools.openapidiff.core.utils;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Changed;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangedUtils_isChangedTest {

    @Test
    void testIsChanged_Unchanged() {
        Changed mockChanged = mock(Changed.class);
        when(mockChanged.isUnchanged()).thenReturn(true);

        Optional<Changed> result = ChangedUtils.isChanged(mockChanged);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testIsChanged_Changed() {
        Changed mockChanged = mock(Changed.class);
        when(mockChanged.isUnchanged()).thenReturn(false);

        Optional<Changed> result = ChangedUtils.isChanged(mockChanged);
        assertEquals(Optional.of(mockChanged), result);
    }
}
