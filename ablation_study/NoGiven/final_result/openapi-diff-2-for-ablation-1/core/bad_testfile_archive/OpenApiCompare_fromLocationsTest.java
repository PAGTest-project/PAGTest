package org.openapitools.openapidiff.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class OpenApiCompare_fromLocationsTest {

  @Test
  public void testFromLocations_Successful() {
    // Given
    String oldLocation = "oldLocation";
    String newLocation = "newLocation";
    List<AuthorizationValue> auths = Collections.emptyList();
    OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

    OpenAPI mockOldSpec = Mockito.mock(OpenAPI.class);
    OpenAPI mockNewSpec = Mockito.mock(OpenAPI.class);

    OpenApiCompare openApiCompare = Mockito.spy(new OpenApiCompare());
    doReturn(mockOldSpec).when(openApiCompare).readLocation(oldLocation, auths);
    doReturn(mockNewSpec).when(openApiCompare).readLocation(newLocation, auths);

    // When
    ChangedOpenApi result = openApiCompare.fromLocations(oldLocation, newLocation, auths, options);

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

    OpenApiCompare openApiCompare = Mockito.spy(new OpenApiCompare());
    doReturn(null).when(openApiCompare).readLocation(oldLocation, auths);
    doReturn(new OpenAPI()).when(openApiCompare).readLocation(newLocation, auths);

    // When & Then
    assertThrows(
        RuntimeException.class,
        () -> {
          openApiCompare.fromLocations(oldLocation, newLocation, auths, options);
        });
  }
}
