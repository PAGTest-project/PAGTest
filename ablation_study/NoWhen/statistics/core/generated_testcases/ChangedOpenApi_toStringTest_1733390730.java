
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_toStringTest {

    @Test
    public void testToString() {
        // Given
        CompositeConfiguration config = new CompositeConfiguration();
        OpenApiDiffOptions options = new OpenApiDiffOptions(config);
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(options);
        OpenAPI oldSpecOpenApi = new OpenAPI();
        OpenAPI newSpecOpenApi = new OpenAPI();
        List<Endpoint> newEndpoints = Collections.emptyList();
        List<Endpoint> missingEndpoints = Collections.emptyList();
        List<ChangedOperation> changedOperations = Collections.emptyList();
        Map<String, Object> oldExtensions = new HashMap<>();
        Map<String, Object> newExtensions = new HashMap<>();
        DiffContext diffContext = new DiffContext();
        ChangedExtensions changedExtensions = new ChangedExtensions(oldExtensions, newExtensions, diffContext);

        changedOpenApi.setOldSpecOpenApi(oldSpecOpenApi)
                      .setNewSpecOpenApi(newSpecOpenApi)
                      .setNewEndpoints(newEndpoints)
                      .setMissingEndpoints(missingEndpoints)
                      .setChangedOperations(changedOperations)
                      .setChangedExtensions(changedExtensions);

        // When
        String result = changedOpenApi.toString();

        // Then
        String expected = "ChangedOpenApi(oldSpecOpenApi=io.swagger.v3.oas.models.OpenAPI@0, newSpecOpenApi=io.swagger.v3.oas.models.OpenAPI@0, newEndpoints=[], missingEndpoints=[], changedOperations=[], changedExtensions=org.openapitools.openapidiff.core.model.ChangedExtensions@0)";
        assertEquals(expected, result);
    }
}
