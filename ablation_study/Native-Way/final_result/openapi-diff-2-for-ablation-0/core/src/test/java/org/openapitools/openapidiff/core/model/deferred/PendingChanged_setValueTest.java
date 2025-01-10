package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class PendingChanged_setValueTest {

  @Test
  public void testSetValue_FirstTime() {
    PendingChanged<String> pendingChanged = new PendingChanged<>();
    Optional<String> value = Optional.of("test");

    pendingChanged.setValue(value);

    assertTrue(pendingChanged.isValueSet());
    assertTrue(pendingChanged.isPresent());
    assertEquals("test", pendingChanged.get());
  }

  @Test
  public void testSetValue_SecondTime() {
    PendingChanged<String> pendingChanged = new PendingChanged<>();
    Optional<String> value1 = Optional.of("test1");
    Optional<String> value2 = Optional.of("test2");

    pendingChanged.setValue(value1);

    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              pendingChanged.setValue(value2);
            });

    assertEquals(
        "PendingChanged may not be set more than once. Value was already set.",
        exception.getMessage());
  }
}
