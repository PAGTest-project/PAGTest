
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
        DiffContext context = new DiffContext(null);

        Map<String, ApiResponse> increased = new HashMap<>();
        increased.put("200", new ApiResponse().description("OK"));

        Map<String, ApiResponse> missing = new HashMap<>();
        missing.put("404", new ApiResponse().description("Not Found"));

        Map<String, ChangedResponse> changed = new HashMap<>();
        changed.put("500", new ChangedResponse(new ApiResponse(), new ApiResponse(), context));

        ChangedExtensions extensions = new ChangedExtensions(new HashMap<>(), new HashMap<>(), context);

        ChangedApiResponse changedApiResponse = new ChangedApiResponse(oldApiResponses, newApiResponses, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed)
                .setExtensions(extensions);

        String expected = "ChangedApiResponse(oldApiResponses=class ApiResponses {\n" +
                "{}" +
                "extensions: null\n" +
                "}, newApiResponses=class ApiResponses {\n" +
                "{}" +
                "extensions: null\n" +
                "}, context=" + context + ", increased={200=class ApiResponse {\n" +
                "description: OK\n" +
                "headers: null\n" +
                "content: null\n" +
                "links: null\n" +
                "extensions: null\n" +
                "$ref: null\n" +
                "}}, missing={404=class ApiResponse {\n" +
                "description: Not Found\n" +
                "headers: null\n" +
                "content: null\n" +
                "links: null\n" +
                "extensions: null\n" +
                "$ref: null\n" +
                "}}, changed={500=ChangedResponse(oldApiResponse=class ApiResponse {\n" +
                "description: null\n" +
                "headers: null\n" +
                "content: null\n" +
                "links: null\n" +
                "extensions: null\n" +
                "$ref: null\n" +
                "}, newApiResponse=class ApiResponse {\n" +
                "description: null\n" +
                "headers: null\n" +
                "content: null\n" +
                "links: null\n" +
                "extensions: null\n" +
                "$ref: null\n" +
                "}, context=" + context + ", description=null, headers=null, content=null, extensions=null)}, extensions=ChangedExtensions(oldExtensions={}, newExtensions={}, context=" + context + ", increased={}, missing={}, changed={}))";
        assertEquals(expected, changedApiResponse.toString());
    }
}
