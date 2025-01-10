
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.verify;

public class PendingChanged_logResolvedTest {

    @Test
    public void testLogResolved() {
        // Given
        PendingChanged<String> pendingChanged = new PendingChanged<String>() {
            @Override
            public void logResolved() {
                int deferred = getDeferredCounter().get();
                int resolved = getResolvedCounter().get();
                getLog().debug(
                    "Outstanding: {}  Deferred: {}  Resolved {}", deferred - resolved, deferred, resolved);
            }
        };
        Logger mockLog = Mockito.mock(Logger.class);
        pendingChanged.setLog(mockLog);

        AtomicInteger deferredCounter = new AtomicInteger(5);
        AtomicInteger resolvedCounter = new AtomicInteger(3);
        pendingChanged.setDeferredCounter(deferredCounter);
        pendingChanged.setResolvedCounter(resolvedCounter);

        // When
        pendingChanged.logResolved();

        // Then
        verify(mockLog).debug("Outstanding: {}  Deferred: {}  Resolved {}", 2, 5, 3);
    }
}
