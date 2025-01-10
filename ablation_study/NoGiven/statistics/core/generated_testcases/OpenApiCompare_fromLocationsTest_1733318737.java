
package org.openapitools.openapidiff.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OpenApiCompare_fromLocationsTest {

    @Test
    public void testFromLocations_Successful() {
        // Given
        String oldLocation = "oldLocation";
        String newLocation = "newLocation";
        List<AuthorizationValue> auths = Collections.emptyList();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        OpenAPI mockOldSpec = mock(OpenAPI.class);
        OpenAPI mockNewSpec = mock(OpenAPI.class);

        when(OpenApiCompare.readLocation(oldLocation, auths)).thenReturn(mockOldSpec);
        when(OpenApiCompare.readLocation(newLocation, auths)).thenReturn(mockNewSpec);

        // When
        ChangedOpenApi result = OpenApiCompare.fromLocations(oldLocation, newLocation, auths, options);

        // Then
        assertNotNull(result);
    }

    @Test
    public void testFromLocations_NullSpecThrowsException() {
        // Given
        String oldLocation = "oldLocation";
        String newLocation = "newLocation";
        List<AuthorizationValue> auths = Collections.emptyList();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        when(OpenApiCompare.readLocation(oldLocation, auths)).thenReturn(null);
        when(OpenApiCompare.readLocation(newLocation, auths)).thenReturn(new OpenAPI());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            OpenApiCompare.fromLocations(oldLocation, newLocation, auths, options);
        });
    }
}
