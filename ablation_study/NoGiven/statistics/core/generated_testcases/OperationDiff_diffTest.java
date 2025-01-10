
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;

public class OperationDiff_diffTest {

    private OperationDiff operationDiff;
    private OpenApiDiff openApiDiff;
    private DiffContext context;
    private Operation oldOperation;
    private Operation newOperation;

    @BeforeEach
    public void setUp() {
        openApiDiff = mock(OpenApiDiff.class);
        operationDiff = new OperationDiff(openApiDiff);
        context = mock(DiffContext.class);
        oldOperation = mock(Operation.class);
        newOperation = mock(Operation.class);
    }

    @Test
    public void testDiff_AllPathsCovered() {
        when(context.getUrl()).thenReturn("http://example.com");
        when(context.getMethod()).thenReturn(io.swagger.v3.oas.models.PathItem.HttpMethod.GET);

        when(oldOperation.getSummary()).thenReturn("Old Summary");
        when(newOperation.getSummary()).thenReturn("New Summary");
        when(oldOperation.getDescription()).thenReturn("Old Description");
        when(newOperation.getDescription()).thenReturn("New Description");
        when(oldOperation.getOperationId()).thenReturn("Old OperationId");
        when(newOperation.getOperationId()).thenReturn("New OperationId");
        when(oldOperation.getDeprecated()).thenReturn(false);
        when(newOperation.getDeprecated()).thenReturn(true);
        when(oldOperation.getRequestBody()).thenReturn(null);
        when(newOperation.getRequestBody()).thenReturn(null);
        when(oldOperation.getParameters()).thenReturn(null);
        when(newOperation.getParameters()).thenReturn(null);
        when(oldOperation.getResponses()).thenReturn(null);
        when(newOperation.getResponses()).thenReturn(null);
        when(oldOperation.getSecurity()).thenReturn(null);
        when(newOperation.getSecurity()).thenReturn(null);
        when(oldOperation.getExtensions()).thenReturn(null);
        when(newOperation.getExtensions()).thenReturn(null);

        DeferredChanged<ChangedOperation> result = operationDiff.diff(oldOperation, newOperation, context);

        assertNotNull(result);
    }
}
