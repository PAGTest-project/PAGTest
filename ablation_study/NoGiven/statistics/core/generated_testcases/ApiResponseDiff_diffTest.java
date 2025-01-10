
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.Changed;
import org.openapitools.openapidiff.core.model.ChangedApiResponse;
import org.openapitools.openapidiff.core.model.ChangedExtensions;
import org.openapitools.openapidiff.core.model.ChangedResponse;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredBuilder;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiResponseDiff_diffTest {

    @Test
    public void testDiff_WithSharedResponseCodes() {
        // Given
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        ApiResponseDiff apiResponseDiff = new ApiResponseDiff(openApiDiff);

        ApiResponses left = new ApiResponses();
        ApiResponses right = new ApiResponses();
        left.addApiResponse("200", new ApiResponse().description("OK"));
        right.addApiResponse("200", new ApiResponse().description("OK"));

        DiffContext context = mock(DiffContext.class);
        MapKeyDiff<String, ApiResponse> mapKeyDiff = mock(MapKeyDiff.class);
        when(mapKeyDiff.getSharedKey()).thenReturn(Collections.singletonList("200"));
        when(mapKeyDiff.getIncreased()).thenReturn(Collections.emptyMap());
        when(mapKeyDiff.getMissing()).thenReturn(Collections.emptyMap());
        when(MapKeyDiff.diff(left, right)).thenReturn(mapKeyDiff);

        DeferredChanged<ChangedResponse> changedResponseDeferred = mock(DeferredChanged.class);
        ChangedResponse changedResponse = mock(ChangedResponse.class);
        when(changedResponseDeferred.get()).thenReturn(changedResponse);
        ResponseDiff responseDiff = mock(ResponseDiff.class);
        when(openApiDiff.getResponseDiff()).thenReturn(responseDiff);
        when(responseDiff.diff(left.get("200"), right.get("200"), context))
                .thenReturn(changedResponseDeferred);

        DeferredChanged<ChangedExtensions> changedExtensionsDeferred = mock(DeferredChanged.class);
        ChangedExtensions changedExtensions = mock(ChangedExtensions.class);
        when(changedExtensionsDeferred.get()).thenReturn(changedExtensions);
        ExtensionsDiff extensionsDiff = mock(ExtensionsDiff.class);
        when(openApiDiff.getExtensionsDiff()).thenReturn(extensionsDiff);
        when(extensionsDiff.diff(left.getExtensions(), right.getExtensions(), context))
                .thenReturn(changedExtensionsDeferred);

        // When
        DeferredChanged<ChangedApiResponse> result = apiResponseDiff.diff(left, right, context);

        // Then
        ChangedApiResponse changedApiResponse = result.get();
        assertEquals(1, changedApiResponse.getChanged().size());
        assertEquals(changedResponse, changedApiResponse.getChanged().get("200"));
        assertEquals(changedExtensions, changedApiResponse.getExtensions());
    }
}
