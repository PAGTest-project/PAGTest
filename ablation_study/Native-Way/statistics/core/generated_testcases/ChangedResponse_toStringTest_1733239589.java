
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedResponse_toStringTest {

    @Test
    public void testToString() {
        ApiResponse oldApiResponse = new ApiResponse().description("Old Response");
        ApiResponse newApiResponse = new ApiResponse().description("New Response");
        DiffContext context = new DiffContext(null);
        ChangedMetadata description = new ChangedMetadata();
        ChangedHeaders headers = new ChangedHeaders(null, null, context);
        ChangedContent content = new ChangedContent(null, null, context);
        ChangedExtensions extensions = new ChangedExtensions(null, null, context);

        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context)
                .setDescription(description)
                .setHeaders(headers)
                .setContent(content)
                .setExtensions(extensions);

        String expected = "ChangedResponse(oldApiResponse=Old Response, newApiResponse=New Response, context="
                + context
                + ", description="
                + description
                + ", headers="
                + headers
                + ", content="
                + content
                + ", extensions="
                + extensions
                + ")";

        assertEquals(expected, changedResponse.toString());
    }
}
