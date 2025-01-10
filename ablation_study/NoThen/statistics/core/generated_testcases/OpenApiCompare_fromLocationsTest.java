
package org.openapitools.openapidiff.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class OpenApiCompare_fromLocationsTest {

    @Test
    public void testFromLocations() {
        // Given
        String oldLocation = "oldLocation";
        String newLocation = "newLocation";
        List<AuthorizationValue> auths = Collections.emptyList();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        OpenAPI mockOldSpec = Mockito.mock(OpenAPI.class);
        OpenAPI mockNewSpec = Mockito.mock(OpenAPI.class);

        when(OpenApiCompare.readLocation(oldLocation, auths)).thenReturn(mockOldSpec);
        when(OpenApiCompare.readLocation(newLocation, auths)).thenReturn(mockNewSpec);

        // When
        ChangedOpenApi result = OpenApiCompare.fromLocations(oldLocation, newLocation, auths, options);

        // Then
        assertNotNull(result);
    }
}
