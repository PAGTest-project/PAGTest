
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponses;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangedApiResponse_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedApiResponse response = new ChangedApiResponse(new ApiResponses(), new ApiResponses(), new DiffContext(null));
        assertTrue(response.equals(response));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedApiResponse response = new ChangedApiResponse(new ApiResponses(), new ApiResponses(), new DiffContext(null));
        assertFalse(response.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedApiResponse response = new ChangedApiResponse(new ApiResponses(), new ApiResponses(), new DiffContext(null));
        assertFalse(response.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        ApiResponses oldResponses = new ApiResponses();
        ApiResponses newResponses = new ApiResponses();
        DiffContext context = new DiffContext(null);
        ChangedApiResponse response1 = new ChangedApiResponse(oldResponses, newResponses, context);
        ChangedApiResponse response2 = new ChangedApiResponse(new ApiResponses(), new ApiResponses(), new DiffContext(null));
        response1.setIncreased(new LinkedHashMap<>());
        response1.setMissing(new LinkedHashMap<>());
        response1.setChanged(new LinkedHashMap<>());
        response2.setIncreased(new LinkedHashMap<>());
        response2.setMissing(new LinkedHashMap<>());
        response2.setChanged(new LinkedHashMap<>());
        assertFalse(response1.equals(response2));
    }

    @Test
    public void testEquals_SameFields() {
        ApiResponses oldResponses = new ApiResponses();
        ApiResponses newResponses = new ApiResponses();
        DiffContext context = new DiffContext(null);
        ChangedApiResponse response1 = new ChangedApiResponse(oldResponses, newResponses, context);
        ChangedApiResponse response2 = new ChangedApiResponse(oldResponses, newResponses, context);
        response1.setIncreased(new LinkedHashMap<>());
        response1.setMissing(new LinkedHashMap<>());
        response1.setChanged(new LinkedHashMap<>());
        response2.setIncreased(new LinkedHashMap<>());
        response2.setMissing(new LinkedHashMap<>());
        response2.setChanged(new LinkedHashMap<>());
        assertTrue(response1.equals(response2));
    }
}
