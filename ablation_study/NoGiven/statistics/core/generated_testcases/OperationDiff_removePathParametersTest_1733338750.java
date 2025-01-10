
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedParameters;
import org.openapitools.openapidiff.core.model.DiffContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class OperationDiff_removePathParametersTest {

    @Test
    public void testRemovePathParameters() {
        // Given
        OperationDiff operationDiff = new OperationDiff(null);
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("oldParam1", "newParam1");
        pathParameters.put("oldParam2", "newParam2");

        ChangedParameters params = mock(ChangedParameters.class);
        List<Parameter> missing = mock(List.class);
        List<Parameter> increased = mock(List.class);

        when(params.getMissing()).thenReturn(missing);
        when(params.getIncreased()).thenReturn(increased);

        // When
        operationDiff.removePathParameters(pathParameters, params);

        // Then
        verify(missing, times(1)).remove(any());
        verify(increased, times(1)).remove(any());
    }
}
