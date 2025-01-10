
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
        String oldLocation = "http://example.com/old-api.json";
        String newLocation = "http://example.com/new-api.json";
        List<AuthorizationValue> auths = Collections.emptyList();

        // When
        ChangedOpenApi result = OpenApiCompare.fromLocations(oldLocation, newLocation, auths);

        // Then
        assertNotNull(result);
    }
}
