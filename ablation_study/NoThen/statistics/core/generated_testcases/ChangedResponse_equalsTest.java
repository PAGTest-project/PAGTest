
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangedResponse_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null);
        ChangedResponse response = new ChangedResponse(oldApiResponse, newApiResponse, context);

        assertTrue(response.equals(response));
    }

    @Test
    public void testEquals_NullObject() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null);
        ChangedResponse response = new ChangedResponse(oldApiResponse, newApiResponse, context);

        assertFalse(response.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null);
        ChangedResponse response = new ChangedResponse(oldApiResponse, newApiResponse, context);

        assertFalse(response.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        ApiResponse oldApiResponse1 = new ApiResponse();
        ApiResponse newApiResponse1 = new ApiResponse();
        DiffContext context1 = new DiffContext(null);
        ChangedResponse response1 = new ChangedResponse(oldApiResponse1, newApiResponse1, context1);

        ApiResponse oldApiResponse2 = new ApiResponse();
        ApiResponse newApiResponse2 = new ApiResponse();
        DiffContext context2 = new DiffContext(null);
        ChangedResponse response2 = new ChangedResponse(oldApiResponse2, newApiResponse2, context2);

        // Set different fields to ensure they are not equal
        response1.setDescription(new ChangedMetadata("description1"));
        response2.setDescription(new ChangedMetadata("description2"));

        assertFalse(response1.equals(response2));
    }

    @Test
    public void testEquals_SameFields() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(null);
        ChangedResponse response1 = new ChangedResponse(oldApiResponse, newApiResponse, context);
        ChangedResponse response2 = new ChangedResponse(oldApiResponse, newApiResponse, context);

        // Set the same fields to ensure they are equal
        response1.setDescription(new ChangedMetadata("description"));
        response2.setDescription(new ChangedMetadata("description"));

        assertTrue(response1.equals(response2));
    }
}
