
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedResponse_isCoreChangedTest {

    @Test
    public void testIsCoreChanged() {
        // Given
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        CompositeConfiguration config = new CompositeConfiguration();
        OpenApiDiffOptions options = new OpenApiDiffOptions(config);
        DiffContext context = new DiffContext(options);
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);

        // When
        DiffResult result = changedResponse.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }
}
