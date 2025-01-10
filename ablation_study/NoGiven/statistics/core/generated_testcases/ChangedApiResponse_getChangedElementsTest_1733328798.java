
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedApiResponse_getChangedElementsTest {

    @Test
    public void testGetChangedElements() {
        // Given
        ApiResponses oldApiResponses = new ApiResponses();
        ApiResponses newApiResponses = new ApiResponses();
        DiffContext context = new DiffContext();
        ChangedApiResponse changedApiResponse = new ChangedApiResponse(oldApiResponses, newApiResponses, context);

        Map<String, ApiResponse> increased = new HashMap<>();
        increased.put("200", new ApiResponse().description("Success"));
        changedApiResponse.setIncreased(increased);

        Map<String, ApiResponse> missing = new HashMap<>();
        missing.put("404", new ApiResponse().description("Not Found"));
        changedApiResponse.setMissing(missing);

        Map<String, ChangedResponse> changed = new HashMap<>();
        changed.put("500", new ChangedResponse());
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
