package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PendingChanged_flatMapTest {

  private PendingChanged<String> pendingChanged;
  private Function<Optional<String>, DeferredChanged<Integer>> mockFunction;
  private DeferredChanged<Integer> mockDeferredChanged;

  @BeforeEach
  public void setUp() {
    pendingChanged = new PendingChanged<>();
    mockFunction = Mockito.mock(Function.class);
    mockDeferredChanged = Mockito.mock(DeferredChanged.class);
  }

  @Test
  public void testFlatMapWithValueSet() {
    // Given
    pendingChanged.setValue(Optional.of("test"));
    when(mockFunction.apply(any())).thenReturn(mockDeferredChanged);

    // When
    DeferredChanged<Integer> result = pendingChanged.flatMap(mockFunction);

    // Then
    verify(mockFunction).apply(Optional.of("test"));
    verify(mockDeferredChanged).whenSet(any());
    assertEquals(mockDeferredChanged, result);
  }

  @Test
  public void testFlatMapWithoutValueSet() {
    // Given
    when(mockFunction.apply(any())).thenReturn(mockDeferredChanged);

    // When
    DeferredChanged<Integer> result = pendingChanged.flatMap(mockFunction);

    // Then
    verify(mockFunction, never()).apply(any());
    verify(mockDeferredChanged, never()).whenSet(any());
    assertEquals(PendingChanged.class, result.getClass());
  }
}
