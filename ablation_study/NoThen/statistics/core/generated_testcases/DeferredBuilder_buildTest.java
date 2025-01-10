
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

    private DeferredBuilder<String> builder;

    @Mock
    private DeferredChanged<String> deferred1;

    @Mock
    private DeferredChanged<String> deferred2;

    @Mock
    private PendingChanged<List<Optional<? super String>>> pendingChanged;

    @BeforeEach
    public void setUp() {
        builder = new DeferredBuilder<>();
    }

    @Test
    public void testBuild_WithEmptyDeferredValues() {
        DeferredChanged<List<Optional<? super String>>> result = builder.build();
        assertEquals(DeferredChanged.empty(), result);
    }

    @Test
    public void testBuild_WithNonEmptyDeferredValues() {
        builder.add(deferred1);
        builder.add(deferred2);

        DeferredChanged<List<Optional<? super String>>> result = builder.build();

        verify(deferred1).whenSet(any());
        verify(deferred2).whenSet(any());
    }
}
