
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

class ChangedResponse_setDescriptionTest {

    @Test
    void testSetDescription() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedMetadata description = new ChangedMetadata();

        // When
        ChangedResponse result = changedResponse.setDescription(description);

        // Then
        assertSame(description, result.getDescription());
        assertSame(changedResponse, result);
    }
}
