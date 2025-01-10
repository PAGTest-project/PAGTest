
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DeferredBuilder_buildTest {

    private DeferredBuilder<Object> builder;

    @Mock
    private DeferredChanged<Object> deferredItem1;

    @Mock
    private DeferredChanged<Object> deferredItem2;

    @Mock
    private PendingChanged<List<Optional<? super Object>>> pendingChanged;

    @BeforeEach
    public void setUp() {
        builder = new DeferredBuilder<>();
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

        when(pendingChanged.setValue(any())).thenReturn(true);

        DeferredChanged<List<Optional<? super Object>>> result = builder.build();

        verify(deferredItem1).whenSet(any());
        verify(deferredItem2).whenSet(any());
    }
}
