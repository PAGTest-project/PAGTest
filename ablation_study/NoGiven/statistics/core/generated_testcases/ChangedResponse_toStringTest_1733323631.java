
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

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
        context = new DiffContext(new OpenApiDiffOptions());
        description = new ChangedMetadata();
        
        Map<String, io.swagger.v3.oas.models.headers.Header> oldHeadersMap = new HashMap<>();
        Map<String, io.swagger.v3.oas.models.headers.Header> newHeadersMap = new HashMap<>();
        headers = new ChangedHeaders(oldHeadersMap, newHeadersMap, context);
        
        io.swagger.v3.oas.models.media.Content oldContentMap = new io.swagger.v3.oas.models.media.Content();
        io.swagger.v3.oas.models.media.Content newContentMap = new io.swagger.v3.oas.models.media.Content();
        content = new ChangedContent(oldContentMap, newContentMap, context);
        
        Map<String, Object> oldExtensionsMap = new HashMap<>();
        Map<String, Object> newExtensionsMap = new HashMap<>();
        extensions = new ChangedExtensions(oldExtensionsMap, newExtensionsMap, context);

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
