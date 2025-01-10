package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PendingChanged_flatMapTest {

  @Mock private Function<Optional<String>, DeferredChanged<Integer>> mockFunction;

  @Mock private DeferredChanged<Integer> mockDeferredChanged;

  @Test
  public void testFlatMapWithValueSet() {
    PendingChanged<String> pendingChanged = new PendingChanged<>();
    pendingChanged.setValue(Optional.of("test"));

    when(mockFunction.apply(any())).thenReturn(mockDeferredChanged);

    DeferredChanged<Integer> result = pendingChanged.flatMap(mockFunction);

    verify(mockFunction).apply(Optional.of("test"));
    verify(mockDeferredChanged).whenSet(any());
    assertEquals(mockDeferredChanged, result);
  }

  @Test
  public void testFlatMapWithoutValueSet() {
    PendingChanged<String> pendingChanged = new PendingChanged<>();

    when(mockFunction.apply(any())).thenReturn(mockDeferredChanged);

    DeferredChanged<Integer> result = pendingChanged.flatMap(mockFunction);

    verify(mockFunction, never()).apply(any());
    verify(mockDeferredChanged, never()).whenSet(any());
    assertEquals(PendingChanged.class, result.getClass());
  }
}
