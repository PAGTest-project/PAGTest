package org.openapitools.openapidiff.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OpenApiCompare_fromLocationsTest {

  @Test
  public void testFromLocations_Success() {
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

  @Test
  public void testFromLocations_NullSpec() {
    // Given
    String oldLocation = "oldLocation";
    String newLocation = "newLocation";
    List<AuthorizationValue> auths = Collections.emptyList();
    OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

    when(OpenApiCompare.readLocation(oldLocation, auths)).thenReturn(null);
    when(OpenApiCompare.readLocation(newLocation, auths)).thenReturn(null);

    // When & Then
    assertThrows(
        RuntimeException.class,
        () -> {
          OpenApiCompare.fromLocations(oldLocation, newLocation, auths, options);
        });
  }
}
