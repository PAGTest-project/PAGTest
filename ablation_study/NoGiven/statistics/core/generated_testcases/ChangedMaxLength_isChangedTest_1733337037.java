
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;

public class ChangedMaxLength_isChangedTest {

    @Test
    public void testNoChanges() {
        DiffContext context = mock(DiffContext.class);
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(10, 10, context);
        assertEquals(DiffResult.NO_CHANGES, changedMaxLength.isChanged());
    }

    @Test
    public void testIncompatibleRequestDecreased() {
        DiffContext context = mock(DiffContext.class);
        when(context.isRequest()).thenReturn(true);
        when(REQUEST_MAX_LENGTH_DECREASED.enabled(context)).thenReturn(true);
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(10, 5, context);
        assertEquals(DiffResult.INCOMPATIBLE, changedMaxLength.isChanged());
    }

    @Test
    public void testIncompatibleResponseIncreased() {
        DiffContext context = mock(DiffContext.class);
        when(context.isResponse()).thenReturn(true);
        when(RESPONSE_MAX_LENGTH_INCREASED.enabled(context)).thenReturn(true);
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(5, 10, context);
        assertEquals(DiffResult.INCOMPATIBLE, changedMaxLength.isChanged());
    }

    @Test
    public void testCompatibleRequest() {
        DiffContext context = mock(DiffContext.class);
        when(context.isRequest()).thenReturn(true);
        when(REQUEST_MAX_LENGTH_DECREASED.enabled(context)).thenReturn(false);
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(10, 5, context);
        assertEquals(DiffResult.COMPATIBLE, changedMaxLength.isChanged());
    }

    @Test
    public void testCompatibleResponse() {
        DiffContext context = mock(DiffContext.class);
        when(context.isResponse()).thenReturn(true);
        when(RESPONSE_MAX_LENGTH_INCREASED.enabled(context)).thenReturn(false);
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(5, 10, context);
        assertEquals(DiffResult.COMPATIBLE, changedMaxLength.isChanged());
    }
}
