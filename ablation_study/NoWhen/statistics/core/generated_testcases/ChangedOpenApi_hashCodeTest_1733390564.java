
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_hashCodeTest {

    @Test
    public void testHashCode() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        OpenAPI oldSpecOpenApi = new OpenAPI();
        OpenAPI newSpecOpenApi = new OpenAPI();
        List<Endpoint> newEndpoints = Collections.emptyList();
        List<Endpoint> missingEndpoints = Collections.emptyList();
        List<ChangedOperation> changedOperations = Collections.emptyList();
        ChangedExtensions changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);

        ChangedOpenApi changedOpenApi = new ChangedOpenApi(options)
                .setOldSpecOpenApi(oldSpecOpenApi)
                .setNewSpecOpenApi(newSpecOpenApi)
                .setNewEndpoints(newEndpoints)
                .setMissingEndpoints(missingEndpoints)
                .setChangedOperations(changedOperations)
                .setChangedExtensions(changedExtensions);

        int expectedHashCode = Objects.hash(
                oldSpecOpenApi,
                newSpecOpenApi,
                newEndpoints,
                missingEndpoints,
                changedOperations,
                changedExtensions
        );

        assertEquals(expectedHashCode, changedOpenApi.hashCode());
    }
}
