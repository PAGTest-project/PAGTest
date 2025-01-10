
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedApiResponse_getChangedElementsTest {

    @Test
    public void testGetChangedElements() {
        // Given
        ApiResponses oldApiResponses = new ApiResponses();
        ApiResponses newApiResponses = new ApiResponses();
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        ChangedApiResponse changedApiResponse = new ChangedApiResponse(oldApiResponses, newApiResponses, context);

        Map<String, ApiResponse> increased = new HashMap<>();
        increased.put("200", new ApiResponse().description("Success"));
        changedApiResponse.setIncreased(increased);

        Map<String, ApiResponse> missing = new HashMap<>();
        missing.put("404", new ApiResponse().description("Not Found"));
        changedApiResponse.setMissing(missing);

        Map<String, ChangedResponse> changed = new HashMap<>();
        changed.put("500", new ChangedResponse(new ApiResponse(), new ApiResponse(), context));
        changedApiResponse.setChanged(changed);

        ChangedExtensions extensions = new ChangedExtensions(new HashMap<>(), new HashMap<>(), context);
        changedApiResponse.setExtensions(extensions);

        // When
        List<Changed> changedElements = changedApiResponse.getChangedElements();

        // Then
        assertEquals(2, changedElements.size());
        assertTrue(changedElements.contains(extensions));
    }
}
