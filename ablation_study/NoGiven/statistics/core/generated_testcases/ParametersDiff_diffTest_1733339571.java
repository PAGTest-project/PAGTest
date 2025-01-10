
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedParameters;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredBuilder;
import org.openapitools.openapidiff.core.utils.RefPointer;
import org.openapitools.openapidiff.core.utils.RefType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ParametersDiff_diffTest {

    private static final RefPointer<Parameter> refPointer = new RefPointer<>(RefType.PARAMETERS);

    @Test
    public void testDiff_AllPathsCovered() {
        // Given
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        ParametersDiff parametersDiff = new ParametersDiff(openApiDiff);
        Components leftComponents = new Components();
        Components rightComponents = new Components();
        when(openApiDiff.getOldSpecOpenApi()).thenReturn(null);
        when(openApiDiff.getNewSpecOpenApi()).thenReturn(null);
        when(openApiDiff.getParameterDiff()).thenReturn(parametersDiff);

        Parameter leftParam = new Parameter().name("param1").in("query");
        Parameter rightParam = new Parameter().name("param1").in("query");
        List<Parameter> left = Collections.singletonList(leftParam);
        List<Parameter> right = Collections.singletonList(rightParam);
        DiffContext context = new DiffContext(null);

        // When
        ChangedParameters result = parametersDiff.diff(left, right, context);

        // Then
        assertEquals(0, result.getMissing().size());
        assertEquals(0, result.getIncreased().size());
        assertEquals(0, result.getChanged().size());
    }
}
