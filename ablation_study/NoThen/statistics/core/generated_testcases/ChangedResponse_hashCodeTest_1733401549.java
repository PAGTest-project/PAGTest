
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedResponse_hashCodeTest {

    @Test
    public void testHashCode() {
        ApiResponse oldApiResponse = new ApiResponse().description("Old Response");
        ApiResponse newApiResponse = new ApiResponse().description("New Response");
        DiffContext context = new DiffContext(null);
        ChangedResponse changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);

        int expectedHashCode = Objects.hash(oldApiResponse, newApiResponse, context, null, null, null, null);
        assertEquals(expectedHashCode, changedResponse.hashCode());
    }
}
