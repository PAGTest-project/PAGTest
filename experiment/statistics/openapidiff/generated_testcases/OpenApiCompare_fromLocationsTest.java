
package org.openapitools.openapidiff.core;

import io.swagger.v3.parser.core.models.AuthorizationValue;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenApiCompare_fromLocationsTest {

    @Test
    public void testFromLocations() {
        // Given
        String oldLocation = "src/test/resources/old-api.json";
        String newLocation = "src/test/resources/new-api.json";
        List<AuthorizationValue> auths = Collections.emptyList();

        // When
        ChangedOpenApi result = OpenApiCompare.fromLocations(oldLocation, newLocation, auths);

        // Then
        assertNotNull(result, "Cannot read old OpenAPI spec");
    }
}
