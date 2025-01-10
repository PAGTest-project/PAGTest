
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedResponse_setHeadersTest {

    @Test
    void testSetHeaders() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext();
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedHeaders headers = new ChangedHeaders(null, null, context);

        // When
        ChangedResponse result = changedResponse.setHeaders(headers);

        // Then
        assertSame(headers, result.getHeaders());
        assertSame(changedResponse, result);
    }
}
