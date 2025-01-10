package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeferredBuilder_buildTest {

  private DeferredBuilder<String> builder;

  @Mock private DeferredChanged<String> deferred1;

  @Mock private DeferredChanged<String> deferred2;

  @BeforeEach
  public void setUp() {
    builder = new DeferredBuilder<>();
  }

  @Test
  public void testBuildWithEmptyDeferredValues() {
    DeferredChanged<List<Optional<? super String>>> result = builder.build();
    assertTrue(result.isEmpty());
  }

  @Test
  public void testBuildWithNonEmptyDeferredValues() {
    builder.add(deferred1);
    builder.add(deferred2);

    PendingChanged<List<Optional<? super String>>> pendingChanged =
        Mockito.mock(PendingChanged.class);
    when(pendingChanged.setValue(any())).thenReturn(true);

    DeferredChanged<List<Optional<? super String>>> result = builder.build();

    verify(deferred1).whenSet(any());
    verify(deferred2).whenSet(any());

    assertEquals(pendingChanged.getClass(), result.getClass());
  }
}
