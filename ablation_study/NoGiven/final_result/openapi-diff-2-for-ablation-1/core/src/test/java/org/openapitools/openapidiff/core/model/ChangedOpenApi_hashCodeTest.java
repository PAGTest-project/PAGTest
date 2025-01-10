package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.OpenAPI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class ChangedOpenApi_hashCodeTest {

  @Test
  public void testHashCode() {
    OpenAPI oldSpec = new OpenAPI();
    OpenAPI newSpec = new OpenAPI();
    List<Endpoint> newEndpoints = Collections.emptyList();
    List<Endpoint> missingEndpoints = Collections.emptyList();
    List<ChangedOperation> changedOperations = Collections.emptyList();
    ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

    ChangedOpenApi changedOpenApi =
        new ChangedOpenApi(null)
            .setOldSpecOpenApi(oldSpec)
            .setNewSpecOpenApi(newSpec)
            .setNewEndpoints(newEndpoints)
            .setMissingEndpoints(missingEndpoints)
            .setChangedOperations(changedOperations)
            .setChangedExtensions(changedExtensions);

    int expectedHashCode =
        Objects.hash(
            oldSpec, newSpec, newEndpoints, missingEndpoints, changedOperations, changedExtensions);
    assertEquals(expectedHashCode, changedOpenApi.hashCode());
  }
}
