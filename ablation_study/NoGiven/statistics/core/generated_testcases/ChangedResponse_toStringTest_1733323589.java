
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedResponse_toStringTest {

    private ChangedResponse changedResponse;
    private ApiResponse oldApiResponse;
    private ApiResponse newApiResponse;
    private DiffContext context;
    private ChangedMetadata description;
    private ChangedHeaders headers;
    private ChangedContent content;
    private ChangedExtensions extensions;

    @BeforeEach
    public void setUp() {
        oldApiResponse = new ApiResponse().description("Old Response");
        newApiResponse = new ApiResponse().description("New Response");
        context = new DiffContext();
        description = new ChangedMetadata();
        headers = new ChangedHeaders();
        content = new ChangedContent();
        extensions = new ChangedExtensions();

        changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        changedResponse.setDescription(description);
        changedResponse.setHeaders(headers);
        changedResponse.setContent(content);
        changedResponse.setExtensions(extensions);
    }

    @Test
    public void testToString() {
        String expected = "ChangedResponse(oldApiResponse=Old Response, newApiResponse=New Response, context="
                + context.toString()
                + ", description="
                + description.toString()
                + ", headers="
                + headers.toString()
                + ", content="
                + content.toString()
                + ", extensions="
                + extensions.toString()
                + ")";

        assertEquals(expected, changedResponse.toString());
    }
}
