
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedApiResponse_hashCodeTest {

    @Test
    public void testHashCode() {
        ApiResponses oldApiResponses = new ApiResponses();
        ApiResponses newApiResponses = new ApiResponses();
        DiffContext context = new DiffContext(null);
        Map<String, ApiResponse> increased = new LinkedHashMap<>();
        Map<String, ApiResponse> missing = new LinkedHashMap<>();
        Map<String, ChangedResponse> changed = new LinkedHashMap<>();
        ChangedExtensions extensions = new ChangedExtensions(new LinkedHashMap<>(), new LinkedHashMap<>(), context);

        ChangedApiResponse changedApiResponse = new ChangedApiResponse(oldApiResponses, newApiResponses, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed)
                .setExtensions(extensions);

        int expectedHashCode = Objects.hash(oldApiResponses, newApiResponses, context, increased, missing, changed, extensions);
        assertEquals(expectedHashCode, changedApiResponse.hashCode());
    }
}
