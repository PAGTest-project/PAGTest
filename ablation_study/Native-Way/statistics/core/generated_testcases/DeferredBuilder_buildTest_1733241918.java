
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeferredBuilder_buildTest {

    private DeferredBuilder<Object> builder;
    private DeferredChanged<Object> deferredItem1;
    private DeferredChanged<Object> deferredItem2;

    @BeforeEach
    public void setUp() {
        builder = new DeferredBuilder<>();
        deferredItem1 = mock(DeferredChanged.class);
        deferredItem2 = mock(DeferredChanged.class);
    }

    @Test
    public void testBuildWithEmptyDeferredValues() {
        DeferredChanged<List<Optional<? super Object>>> result = builder.build();
        assertEquals(DeferredChanged.empty(), result);
    }

    @Test
    public void testBuildWithNonEmptyDeferredValues() {
        builder.add(deferredItem1);
        builder.add(deferredItem2);

        PendingChanged<List<Optional<? super Object>>> pendingChanged = mock(PendingChanged.class);
        when(pendingChanged.setValue(any())).thenReturn(true);

        DeferredChanged<List<Optional<? super Object>>> result = builder.build();

        verify(deferredItem1).whenSet(any());
        verify(deferredItem2).whenSet(any());
    }
}
