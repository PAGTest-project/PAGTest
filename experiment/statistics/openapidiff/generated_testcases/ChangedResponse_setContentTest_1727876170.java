
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

class ChangedResponse_setContentTest {

    @Test
    void testSetContent() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedContent content = new ChangedContent(null, null, context);

        // When
        ChangedResponse result = changedResponse.setContent(content);

        // Then
        assertSame(content, result.getContent());
        assertSame(changedResponse, result);
    }
}
