package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import io.swagger.v3.oas.models.OpenAPI;
import java.util.Collections;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_hashCodeTest {

  private ChangedOpenApi changedOpenApi;
  private OpenApiDiffOptions options;
  private OpenAPI oldSpecOpenApi;
  private OpenAPI newSpecOpenApi;
  private List<Endpoint> newEndpoints;
  private List<Endpoint> missingEndpoints;
  private List<ChangedOperation> changedOperations;
  private ChangedExtensions changedExtensions;

  @BeforeEach
  public void setUp() {
    options = OpenApiDiffOptions.from(new CompositeConfiguration());
    oldSpecOpenApi = new OpenAPI();
    newSpecOpenApi = new OpenAPI();
    newEndpoints = Collections.emptyList();
    missingEndpoints = Collections.emptyList();
    changedOperations = Collections.emptyList();
    changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);
    changedOpenApi =
        new ChangedOpenApi(options)
            .setOldSpecOpenApi(oldSpecOpenApi)
            .setNewSpecOpenApi(newSpecOpenApi)
            .setNewEndpoints(newEndpoints)
            .setMissingEndpoints(missingEndpoints)
            .setChangedOperations(changedOperations)
            .setChangedExtensions(changedExtensions);
  }

  @Test
  public void testHashCodeWithSameObject() {
    assertEquals(changedOpenApi.hashCode(), changedOpenApi.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentObjects() {
    ChangedOpenApi anotherChangedOpenApi =
        new ChangedOpenApi(options)
            .setOldSpecOpenApi(new OpenAPI())
            .setNewSpecOpenApi(new OpenAPI())
            .setNewEndpoints(Collections.emptyList())
            .setMissingEndpoints(Collections.emptyList())
            .setChangedOperations(Collections.emptyList())
            .setChangedExtensions(
                new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null));
    assertNotEquals(changedOpenApi.hashCode(), anotherChangedOpenApi.hashCode());
  }

  @Test
  public void testHashCodeWithNullFields() {
    ChangedOpenApi nullFieldsChangedOpenApi =
        new ChangedOpenApi(options)
            .setOldSpecOpenApi(null)
            .setNewSpecOpenApi(null)
            .setNewEndpoints(null)
            .setMissingEndpoints(null)
            .setChangedOperations(null)
            .setChangedExtensions(null);
    assertEquals(0, nullFieldsChangedOpenApi.hashCode());
  }
}
