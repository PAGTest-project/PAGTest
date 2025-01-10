
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_toStringTest {

    private ChangedOpenApi changedOpenApi;
    private OpenAPI oldSpecOpenApi;
    private OpenAPI newSpecOpenApi;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions(null);
        changedOpenApi = new ChangedOpenApi(options);
        oldSpecOpenApi = new OpenAPI();
        newSpecOpenApi = new OpenAPI();
    }

    @Test
    public void testToString() {
        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi);
        changedOpenApi.setNewSpecOpenApi(newSpecOpenApi);

        String expected = "ChangedOpenApi(oldSpecOpenApi=" + oldSpecOpenApi + ", newSpecOpenApi=" + newSpecOpenApi + ", newEndpoints=null, missingEndpoints=null, changedOperations=null, changedExtensions=null)";
        assertEquals(expected, changedOpenApi.toString());
    }
}
