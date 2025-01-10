
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        OpenApiDiffOptions options = OpenApiDiffOptions.defaultOptions();
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(options)
                .setOldSpecOpenApi(new OpenAPI())
                .setNewSpecOpenApi(new OpenAPI())
                .setNewEndpoints(Collections.emptyList())
                .setMissingEndpoints(Collections.emptyList())
                .setChangedOperations(Collections.emptyList())
                .setChangedExtensions(new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null));

        // When
        int hashCode = changedOpenApi.hashCode();

        // Then
        assertEquals(Objects.hash(
                changedOpenApi.getOldSpecOpenApi(),
                changedOpenApi.getNewSpecOpenApi(),
                changedOpenApi.getNewEndpoints(),
                changedOpenApi.getMissingEndpoints(),
                changedOpenApi.getChangedOperations(),
                changedOpenApi.getChangedExtensions()
        ), hashCode);
    }
}
