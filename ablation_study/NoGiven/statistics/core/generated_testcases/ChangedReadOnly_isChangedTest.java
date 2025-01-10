
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp;

public class ChangedReadOnly_isChangedTest {

    private DiffContext context;
    private ChangedReadOnly changedReadOnly;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        context = mock(DiffContext.class);
    }

    @Test
    public void testIsChanged_NoChanges() {
        changedReadOnly = new ChangedReadOnly(true, true, context);
        assertEquals(DiffResult.NO_CHANGES, changedReadOnly.isChanged());
    }

    @Test
    public void testIsChanged_Response() {
        when(context.isResponse()).thenReturn(true);
        changedReadOnly = new ChangedReadOnly(true, false, context);
        assertEquals(DiffResult.COMPATIBLE, changedReadOnly.isChanged());
    }

    @Test
    public void testIsChanged_Request_NewValueTrue_Incompatible() {
        when(context.isRequest()).thenReturn(true);
        when(BackwardIncompatibleProp.REQUEST_READONLY_INCREASED.enabled(context)).thenReturn(true);
        changedReadOnly = new ChangedReadOnly(false, true, context);
        assertEquals(DiffResult.INCOMPATIBLE, changedReadOnly.isChanged());
    }

    @Test
    public void testIsChanged_Request_NewValueFalse_Required_Incompatible() {
        when(context.isRequest()).thenReturn(true);
        when(context.isRequired()).thenReturn(true);
        when(BackwardIncompatibleProp.REQUEST_READONLY_REQUIRED_DECREASED.enabled(context)).thenReturn(true);
        changedReadOnly = new ChangedReadOnly(true, false, context);
        assertEquals(DiffResult.INCOMPATIBLE, changedReadOnly.isChanged());
    }

    @Test
    public void testIsChanged_Request_Compatible() {
        when(context.isRequest()).thenReturn(true);
        changedReadOnly = new ChangedReadOnly(false, false, context);
        assertEquals(DiffResult.COMPATIBLE, changedReadOnly.isChanged());
    }

    @Test
    public void testIsChanged_Unknown() {
        changedReadOnly = new ChangedReadOnly(false, false, context);
        assertEquals(DiffResult.UNKNOWN, changedReadOnly.isChanged());
    }
}
