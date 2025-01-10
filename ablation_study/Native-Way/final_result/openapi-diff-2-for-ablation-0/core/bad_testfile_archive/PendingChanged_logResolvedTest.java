package org.openapitools.openapidiff.core.model.deferred;

import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

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
    verify(mockLogger).debug("Outstanding: {}  Deferred: {}  Resolved {}", 0, 1, 1);
  }
}
