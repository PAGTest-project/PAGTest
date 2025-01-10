package org.openapitools.openapidiff.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.Changed;

class ChangedUtils_isChangedTest {

  @Test
  void testIsChanged_Unchanged() {
    Changed mockChanged = Mockito.mock(Changed.class);
    when(mockChanged.isUnchanged()).thenReturn(true);

    Optional<Changed> result = ChangedUtils.isChanged(mockChanged);
    assertEquals(Optional.empty(), result);
  }

  @Test
  void testIsChanged_Changed() {
    Changed mockChanged = Mockito.mock(Changed.class);
    when(mockChanged.isUnchanged()).thenReturn(false);

    Optional<Changed> result = ChangedUtils.isChanged(mockChanged);
    assertEquals(Optional.of(mockChanged), result);
  }
}
