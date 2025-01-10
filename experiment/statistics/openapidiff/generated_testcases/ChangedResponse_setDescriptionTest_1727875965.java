
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedResponse_setDescriptionTest {

    @Test
    void testSetDescription() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext();
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedMetadata description = new ChangedMetadata();

        // When
        ChangedResponse result = changedResponse.setDescription(description);

        // Then
        assertSame(description, result.getDescription());
        assertSame(changedResponse, result);
    }
}
