
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedResponse_equalsTest {

    @Test
    void testEquals_SameInstance() {
        ChangedResponse response = new ChangedResponse(new ApiResponse(), new ApiResponse(), new DiffContext(null));
        assertTrue(response.equals(response));
    }

    @Test
    void testEquals_NullObject() {
        ChangedResponse response = new ChangedResponse(new ApiResponse(), new ApiResponse(), new DiffContext(null));
        assertFalse(response.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        ChangedResponse response = new ChangedResponse(new ApiResponse(), new ApiResponse(), new DiffContext(null));
        assertFalse(response.equals(new Object()));
    }

    @Test
    void testEquals_DifferentFields() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null);
        ChangedResponse response1 = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedResponse response2 = new ChangedResponse(new ApiResponse(), new ApiResponse(), new DiffContext(null));
        assertFalse(response1.equals(response2));
    }

    @Test
    void testEquals_SameFields() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null);
        ChangedResponse response1 = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedResponse response2 = new ChangedResponse(oldApiResponse, newApiResponse, context);
        assertTrue(response1.equals(response2));
    }
}
