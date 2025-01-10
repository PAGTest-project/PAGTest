
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp;
import io.swagger.v3.oas.models.parameters.RequestBody;

public class ChangedRequestBody_isCoreChangedTest {

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        DiffContext context = mock(DiffContext.class);
        ChangedRequestBody changedRequestBody = new ChangedRequestBody(null, null, context);
        changedRequestBody.setChangeRequired(false);

        // When
        DiffResult result = changedRequestBody.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        // Given
        DiffContext context = mock(DiffContext.class);
        when(BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED.enabled(context)).thenReturn(true);
        ChangedRequestBody changedRequestBody = new ChangedRequestBody(null, null, context);
        changedRequestBody.setChangeRequired(true);

        // When
        DiffResult result = changedRequestBody.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        DiffContext context = mock(DiffContext.class);
        when(BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED.enabled(context)).thenReturn(false);
        ChangedRequestBody changedRequestBody = new ChangedRequestBody(null, null, context);
        changedRequestBody.setChangeRequired(true);

        // When
        DiffResult result = changedRequestBody.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }
}
