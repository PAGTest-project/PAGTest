
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedPath;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PathDiff_diffTest {

    @Test
    public void testDiffWithSharedMethods() {
        // Given
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        PathDiff pathDiff = new PathDiff(openApiDiff);

        PathItem left = mock(PathItem.class);
        PathItem right = mock(PathItem.class);
        DiffContext context = mock(DiffContext.class);

        Map<PathItem.HttpMethod, Operation> oldOperationMap = new HashMap<>();
        oldOperationMap.put(PathItem.HttpMethod.GET, new Operation());
        Map<PathItem.HttpMethod, Operation> newOperationMap = new HashMap<>();
        newOperationMap.put(PathItem.HttpMethod.GET, new Operation());

        when(left.readOperationsMap()).thenReturn(oldOperationMap);
        when(right.readOperationsMap()).thenReturn(newOperationMap);
        when(context.getUrl()).thenReturn("/test");
        when(context.getLeftUrl()).thenReturn("/left");
        when(context.getRightUrl()).thenReturn("/right");

        OperationDiff operationDiff = mock(OperationDiff.class);
        ExtensionsDiff extensionsDiff = mock(ExtensionsDiff.class);
        when(openApiDiff.getOperationDiff()).thenReturn(operationDiff);
        when(openApiDiff.getExtensionsDiff()).thenReturn(extensionsDiff);

        // When
        DeferredChanged<ChangedPath> result = pathDiff.diff(left, right, context);

        // Then
        assertNotNull(result);
        verify(operationDiff, times(1)).diff(any(), any(), any());
        verify(extensionsDiff, times(1)).diff(any(), any(), any());
    }
}
