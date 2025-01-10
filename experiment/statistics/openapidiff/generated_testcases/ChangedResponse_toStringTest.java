
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
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        changedResponse.setDescription(new ChangedMetadata());
        changedResponse.setHeaders(new ChangedHeaders(null, null, context));
        changedResponse.setContent(new ChangedContent(null, null, context));
        changedResponse.setExtensions(new ChangedExtensions(null, null, context));

        String expected = "ChangedResponse(oldApiResponse=Old Response, newApiResponse=New Response, context="
                + context.toString()
                + ", description="
                + changedResponse.getDescription().toString()
                + ", headers="
                + changedResponse.getHeaders().toString()
                + ", content="
                + changedResponse.getContent().toString()
                + ", extensions="
                + changedResponse.getExtensions().toString()
                + ")";

        String actual = changedResponse.toString();
        actual = actual.replaceAll("class ApiResponse \\{.*?\\}", "Old Response").replaceAll("class ApiResponse \\{.*?\\}", "New Response");

        assertEquals(expected, actual);
    }
}
