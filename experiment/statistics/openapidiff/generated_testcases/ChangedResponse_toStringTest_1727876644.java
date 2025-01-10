
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedResponse_toStringTest {

    @Test
    public void testToString() {
        ApiResponse oldApiResponse = new ApiResponse().description("Old Response");
        ApiResponse newApiResponse = new ApiResponse().description("New Response");
        DiffContext context = new DiffContext();
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        changedResponse.setDescription(new ChangedMetadata("Description"));
        changedResponse.setHeaders(new ChangedHeaders());
        changedResponse.setContent(new ChangedContent());
        changedResponse.setExtensions(new ChangedExtensions());

        String expected = "ChangedResponse(oldApiResponse=Old Response, newApiResponse=New Response, context="
                + context.toString()
                + ", description=Description, headers="
                + changedResponse.getHeaders().toString()
                + ", content="
                + changedResponse.getContent().toString()
                + ", extensions="
                + changedResponse.getExtensions().toString()
                + ")";

        assertEquals(expected, changedResponse.toString());
    }
}
