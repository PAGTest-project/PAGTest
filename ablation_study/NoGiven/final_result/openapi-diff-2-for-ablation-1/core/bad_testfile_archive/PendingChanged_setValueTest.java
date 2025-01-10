package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Consumer;
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
    Optional<String> value = Optional.of("test");

    pendingChanged.setValue(value);

    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              pendingChanged.setValue(value);
            });

    assertEquals(
        "PendingChanged may not be set more than once. Value was already set.",
        exception.getMessage());
  }

  @Test
  public void testSetValue_EmptyOptional() {
    PendingChanged<String> pendingChanged = new PendingChanged<>();
    Optional<String> value = Optional.empty();

    pendingChanged.setValue(value);

    assertTrue(pendingChanged.isValueSet());
    assertFalse(pendingChanged.isPresent());
    assertNull(pendingChanged.get());
  }

  @Test
  public void testSetValue_Consumers() {
    PendingChanged<String> pendingChanged = new PendingChanged<>();
    Optional<String> value = Optional.of("test");
    Consumer<String> ifPresentConsumer = mock(Consumer.class);
    Consumer<Optional<String>> whenSetConsumer = mock(Consumer.class);

    pendingChanged.ifPresent(ifPresentConsumer);
    pendingChanged.whenSet(whenSetConsumer);

    pendingChanged.setValue(value);

    verify(ifPresentConsumer).accept("test");
    verify(whenSetConsumer).accept(value);
  }
}
