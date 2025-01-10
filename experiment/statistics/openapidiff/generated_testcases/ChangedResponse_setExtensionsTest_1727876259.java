
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedResponse_setExtensionsTest {

    @Test
    void testSetExtensions() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(new OpenApiDiffOptions());
        ChangedExtensions extensions = new ChangedExtensions(null, null, context);
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);

        // When
        ChangedResponse result = changedResponse.setExtensions(extensions);

        // Then
        assertSame(extensions, result.getExtensions());
        assertSame(changedResponse, result);
    }
}
