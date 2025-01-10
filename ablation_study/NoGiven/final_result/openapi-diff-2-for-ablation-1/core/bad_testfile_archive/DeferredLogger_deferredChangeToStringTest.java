package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeferredLogger_deferredChangeToStringTest {

  @Test
  void testDeferredChangeToStringWithValueSetAndPresent() {
    DeferredChanged<?> deferredChanged = Mockito.mock(DeferredChanged.class);
    when(deferredChanged.isValueSet()).thenReturn(true);
    when(deferredChanged.isPresent()).thenReturn(true);
    when(deferredChanged.get()).thenReturn("testValue");
    when(deferredChanged.toString()).thenReturn("DeferredChanged[testValue]");

    String result = DeferredLogger.deferredChangeToString(deferredChanged);
    assertEquals("testValue", result);
  }

  @Test
  void testDeferredChangeToStringWithValueSetAndNotPresent() {
    DeferredChanged<?> deferredChanged = Mockito.mock(DeferredChanged.class);
    when(deferredChanged.isValueSet()).thenReturn(true);
    when(deferredChanged.isPresent()).thenReturn(false);
    when(deferredChanged.toString()).thenReturn("DeferredChanged[empty]");

    String result = DeferredLogger.deferredChangeToString(deferredChanged);
    assertEquals("DeferredChanged[empty]", result);
  }

  @Test
  void testDeferredChangeToStringWithValueNotSet() {
    DeferredChanged<?> deferredChanged = Mockito.mock(DeferredChanged.class);
    when(deferredChanged.isValueSet()).thenReturn(false);
    when(deferredChanged.toString()).thenReturn("DeferredChanged[not set]");

    String result = DeferredLogger.deferredChangeToString(deferredChanged);
    assertEquals("DeferredChanged[not set]", result);
  }
}
