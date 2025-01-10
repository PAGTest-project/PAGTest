
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

        String expected = "ChangedResponse(oldApiResponse=class ApiResponse {\n" +
                "description: Old Response\n" +
                "headers: null\n" +
                "content: null\n" +
                "links: null\n" +
                "extensions: null\n" +
                "$ref: null\n" +
                "}, newApiResponse=class ApiResponse {\n" +
                "description: New Response\n" +
                "headers: null\n" +
                "content: null\n" +
                "links: null\n" +
                "extensions: null\n" +
                "$ref: null\n" +
                "}, context=" + context + ", description=" + description + ", headers=" + headers + ", content=" + content + ", extensions=" + extensions + ")";

        assertEquals(expected, changedResponse.toString());
    }
}
