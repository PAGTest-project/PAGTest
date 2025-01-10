package org.openapitools.openapidiff.core.model.deferred;

import static org.mockito.Mockito.verify;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class PendingChanged_logResolvedTest {

  @Test
  public void testLogResolved() {
    // Given
    PendingChanged<String> pendingChanged =
        new PendingChanged<>() {
          @Override
          public void logResolved() {
            int deferred = deferredCounter.get();
            int resolved = resolvedCounter.get();
            log.debug(
                "Outstanding: {}  Deferred: {}  Resolved {}",
                deferred - resolved,
                deferred,
                resolved);
          }
        };

    Logger mockLog = Mockito.mock(Logger.class);
    pendingChanged.log = mockLog;

    AtomicInteger deferredCounter = new AtomicInteger(5);
    AtomicInteger resolvedCounter = new AtomicInteger(3);
    pendingChanged.deferredCounter = deferredCounter;
    pendingChanged.resolvedCounter = resolvedCounter;

    // When
    pendingChanged.logResolved();

    // Then
    verify(mockLog).debug("Outstanding: {}  Deferred: {}  Resolved {}", 2, 5, 3);
  }
}
