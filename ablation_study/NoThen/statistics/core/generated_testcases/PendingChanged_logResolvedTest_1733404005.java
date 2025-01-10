
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.Optional;

import static org.mockito.Mockito.verify;

public class PendingChanged_logResolvedTest {

    @Test
    public void testLogResolved() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<>();
        Logger mockLogger = Mockito.mock(Logger.class);
        pendingChanged.log = mockLogger;

        // When
        pendingChanged.setValue(Optional.of("testValue"));
        pendingChanged.logResolved();

        // Then
        verify(mockLogger).debug("Outstanding: {}  Deferred: {}  Resolved {}", 0, 0, 1);
    }
}
