
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeferredBuilder_buildTest {

    private DeferredBuilder<String> builder;
    private DeferredChanged<String> deferred1;
    private DeferredChanged<String> deferred2;

    @BeforeEach
    public void setUp() {
        builder = new DeferredBuilder<>();
        deferred1 = mock(DeferredChanged.class);
        deferred2 = mock(DeferredChanged.class);
    }

    @Test
    public void testBuildWithEmptyDeferredValues() {
        DeferredChanged<List<Optional<? super String>>> result = builder.build();
        assertTrue(result instanceof DeferredChanged.EmptyDeferredChanged);
    }

    @Test
    public void testBuildWithNonEmptyDeferredValues() {
        builder.add(deferred1);
        builder.add(deferred2);

        PendingChanged<List<Optional<? super String>>> pendingChanged = mock(PendingChanged.class);
        when(pendingChanged.setValue(any())).thenReturn(true);

        DeferredChanged<List<Optional<? super String>>> result = builder.build();

        verify(deferred1).whenSet(any());
        verify(deferred2).whenSet(any());

        Consumer<Optional<String>> consumer1 = (Consumer<Optional<String>>) result.getWhenSetConsumers().get(0);
        Consumer<Optional<String>> consumer2 = (Consumer<Optional<String>>) result.getWhenSetConsumers().get(1);

        consumer1.accept(Optional.of("value1"));
        consumer2.accept(Optional.of("value2"));

        verify(pendingChanged).setValue(Optional.of(Arrays.asList(Optional.of("value1"), Optional.of("value2"))));
    }
}
