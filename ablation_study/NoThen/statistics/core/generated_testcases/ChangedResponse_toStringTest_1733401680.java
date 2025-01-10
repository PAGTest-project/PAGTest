
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
        changedResponse.setDescription(description)
                       .setHeaders(headers)
                       .setContent(content)
                       .setExtensions(extensions);
    }

    @Test
    public void testToString() {
        String expected = "ChangedResponse(oldApiResponse=io.swagger.v3.oas.models.responses.ApiResponse@, newApiResponse=io.swagger.v3.oas.models.responses.ApiResponse@, context=org.openapitools.openapidiff.core.model.DiffContext@, description=org.openapitools.openapidiff.core.model.ChangedMetadata@, headers=org.openapitools.openapidiff.core.model.ChangedHeaders@, content=org.openapitools.openapidiff.core.model.ChangedContent@, extensions=org.openapitools.openapidiff.core.model.ChangedExtensions@)";
        String actual = changedResponse.toString();
        assertEquals(expected, actual);
    }
}
