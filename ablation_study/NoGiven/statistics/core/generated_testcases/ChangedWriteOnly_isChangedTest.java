
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp;

class ChangedWriteOnly_isChangedTest {

    private DiffContext context;
    private ChangedWriteOnly changedWriteOnly;

    @BeforeEach
    void setUp() {
        context = mock(DiffContext.class);
    }

    @Test
    void testIsChanged_NoChanges() {
        changedWriteOnly = new ChangedWriteOnly(true, true, context);
        assertEquals(DiffResult.NO_CHANGES, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Request() {
        when(context.isRequest()).thenReturn(true);
        changedWriteOnly = new ChangedWriteOnly(true, false, context);
        assertEquals(DiffResult.COMPATIBLE, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Response_NewValueTrue_Incompatible() {
        when(context.isResponse()).thenReturn(true);
        when(BackwardIncompatibleProp.RESPONSE_WRITEONLY_INCREASED.enabled(context)).thenReturn(true);
        changedWriteOnly = new ChangedWriteOnly(false, true, context);
        assertEquals(DiffResult.INCOMPATIBLE, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Response_NewValueTrue_Compatible() {
        when(context.isResponse()).thenReturn(true);
        when(BackwardIncompatibleProp.RESPONSE_WRITEONLY_INCREASED.enabled(context)).thenReturn(false);
        changedWriteOnly = new ChangedWriteOnly(false, true, context);
        assertEquals(DiffResult.COMPATIBLE, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Response_NewValueFalse_Required_Incompatible() {
        when(context.isResponse()).thenReturn(true);
        when(context.isRequired()).thenReturn(true);
        when(BackwardIncompatibleProp.RESPONSE_WRITEONLY_REQUIRED_DECREASED.enabled(context)).thenReturn(true);
        changedWriteOnly = new ChangedWriteOnly(true, false, context);
        assertEquals(DiffResult.INCOMPATIBLE, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Response_NewValueFalse_Required_Compatible() {
        when(context.isResponse()).thenReturn(true);
        when(context.isRequired()).thenReturn(true);
        when(BackwardIncompatibleProp.RESPONSE_WRITEONLY_REQUIRED_DECREASED.enabled(context)).thenReturn(false);
        changedWriteOnly = new ChangedWriteOnly(true, false, context);
        assertEquals(DiffResult.COMPATIBLE, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Response_NewValueFalse_NotRequired_Compatible() {
        when(context.isResponse()).thenReturn(true);
        when(context.isRequired()).thenReturn(false);
        changedWriteOnly = new ChangedWriteOnly(true, false, context);
        assertEquals(DiffResult.COMPATIBLE, changedWriteOnly.isChanged());
    }

    @Test
    void testIsChanged_Unknown() {
        changedWriteOnly = new ChangedWriteOnly(true, false, context);
        assertEquals(DiffResult.UNKNOWN, changedWriteOnly.isChanged());
    }
}
