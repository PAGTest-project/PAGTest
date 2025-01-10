
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;

public class ChangedRequired_isItemsChangedTest {

    private DiffContext context;
    private ChangedRequired changedRequired;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
    }

    @Test
    public void testIsItemsChanged_RequestWithIncreased_Incompatible() {
        List<String> oldValue = Collections.emptyList();
        List<String> newValue = Arrays.asList("item1");
        changedRequired = new ChangedRequired(oldValue, newValue, context);

        when(context.isRequest()).thenReturn(true);
        when(REQUEST_REQUIRED_INCREASED.enabled(context)).thenReturn(true);

        assertEquals(DiffResult.INCOMPATIBLE, changedRequired.isItemsChanged());
    }

    @Test
    public void testIsItemsChanged_ResponseWithMissing_Incompatible() {
        List<String> oldValue = Arrays.asList("item1");
        List<String> newValue = Collections.emptyList();
        changedRequired = new ChangedRequired(oldValue, newValue, context);

        when(context.isResponse()).thenReturn(true);
        when(RESPONSE_REQUIRED_DECREASED.enabled(context)).thenReturn(true);

        assertEquals(DiffResult.INCOMPATIBLE, changedRequired.isItemsChanged());
    }

    @Test
    public void testIsItemsChanged_Compatible() {
        List<String> oldValue = Collections.emptyList();
        List<String> newValue = Collections.emptyList();
        changedRequired = new ChangedRequired(oldValue, newValue, context);

        when(context.isRequest()).thenReturn(false);
        when(context.isResponse()).thenReturn(false);

        assertEquals(DiffResult.COMPATIBLE, changedRequired.isItemsChanged());
    }
}
