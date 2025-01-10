
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class ChangedApiResponse_toStringTest {

    @Test
    public void testToString() {
        ApiResponses oldApiResponses = new ApiResponses();
        ApiResponses newApiResponses = new ApiResponses();
        DiffContext context = new DiffContext();

        Map<String, ApiResponse> increased = new HashMap<>();
        increased.put("200", new ApiResponse().description("OK"));

        Map<String, ApiResponse> missing = new HashMap<>();
        missing.put("404", new ApiResponse().description("Not Found"));

        Map<String, ChangedResponse> changed = new HashMap<>();
        changed.put("500", new ChangedResponse());

        ChangedExtensions extensions = new ChangedExtensions();

        ChangedApiResponse changedApiResponse = new ChangedApiResponse(oldApiResponses, newApiResponses, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed)
                .setExtensions(extensions);

        String expected = "ChangedApiResponse(oldApiResponses={}, newApiResponses={}, context=" + context + ", increased={200=ApiResponse{description=OK}}, missing={404=ApiResponse{description=Not Found}}, changed={500=ChangedResponse{}}, extensions=ChangedExtensions{})";
        assertEquals(expected, changedApiResponse.toString());
    }
}
