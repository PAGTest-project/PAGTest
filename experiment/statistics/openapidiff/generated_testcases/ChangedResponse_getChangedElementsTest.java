
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedResponse_getChangedElementsTest {

    @Test
    public void testGetChangedElements() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null); // Provide the required parameter
        ChangedMetadata description = new ChangedMetadata();
        ChangedHeaders headers = new ChangedHeaders(null, null, context);
        ChangedContent content = new ChangedContent(null, null, context);
        ChangedExtensions extensions = new ChangedExtensions(null, null, context);

        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context)
                .setDescription(description)
                .setHeaders(headers)
                .setContent(content)
                .setExtensions(extensions);

        // When
        List<Changed> changedElements = changedResponse.getChangedElements();

        // Then
        assertEquals(Arrays.asList(description, headers, content, extensions), changedElements);
    }
}
