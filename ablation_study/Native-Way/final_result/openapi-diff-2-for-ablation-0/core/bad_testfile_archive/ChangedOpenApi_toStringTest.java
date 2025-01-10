package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.OpenAPI;
import java.util.Collections;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_toStringTest {

  private ChangedOpenApi changedOpenApi;
  private OpenAPI oldSpecOpenApi;
  private OpenAPI newSpecOpenApi;
  private List<Endpoint> newEndpoints;
  private List<Endpoint> missingEndpoints;
  private List<ChangedOperation> changedOperations;
  private ChangedExtensions changedExtensions;

  @BeforeEach
  public void setUp() {
    CompositeConfiguration config = new CompositeConfiguration();
    changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions(config));
    oldSpecOpenApi = new OpenAPI();
    newSpecOpenApi = new OpenAPI();
    newEndpoints = Collections.emptyList();
    missingEndpoints = Collections.emptyList();
    changedOperations = Collections.emptyList();
    changedExtensions =
        new ChangedExtensions(
            Collections.emptyMap(),
            Collections.emptyMap(),
            new DiffContext(new OpenApiDiffOptions(config)));

    changedOpenApi
        .setOldSpecOpenApi(oldSpecOpenApi)
        .setNewSpecOpenApi(newSpecOpenApi)
        .setNewEndpoints(newEndpoints)
        .setMissingEndpoints(missingEndpoints)
        .setChangedOperations(changedOperations)
        .setChangedExtensions(changedExtensions);
  }

  @Test
  public void testToString() {
    String expected =
        "ChangedOpenApi(oldSpecOpenApi="
            + oldSpecOpenApi
            + ", newSpecOpenApi="
            + newSpecOpenApi
            + ", newEndpoints="
            + newEndpoints
            + ", missingEndpoints="
            + missingEndpoints
            + ", changedOperations="
            + changedOperations
            + ", changedExtensions="
            + changedExtensions
            + ")";
    assertEquals(expected, changedOpenApi.toString());
  }

  @Test
  public void testToStringWithNullValues() {
    changedOpenApi
        .setOldSpecOpenApi(null)
        .setNewSpecOpenApi(null)
        .setNewEndpoints(null)
        .setMissingEndpoints(null)
        .setChangedOperations(null)
        .setChangedExtensions(null);

    String expected =
        "ChangedOpenApi(oldSpecOpenApi=null"
            + ", newSpecOpenApi=null"
            + ", newEndpoints=null"
            + ", missingEndpoints=null"
            + ", changedOperations=null"
            + ", changedExtensions=null"
            + ")";
    assertEquals(expected, changedOpenApi.toString());
  }

  @Test
  public void testToStringWithNonEmptyValues() {
    newEndpoints = Collections.singletonList(new Endpoint());
    missingEndpoints = Collections.singletonList(new Endpoint());
    changedOperations =
        Collections.singletonList(
            new ChangedOperation(
                "path",
                io.swagger.v3.oas.models.PathItem.HttpMethod.GET,
                new io.swagger.v3.oas.models.Operation(),
                new io.swagger.v3.oas.models.Operation()));
    changedExtensions =
        new ChangedExtensions(
            Collections.emptyMap(),
            Collections.emptyMap(),
            new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));

    changedOpenApi
        .setNewEndpoints(newEndpoints)
        .setMissingEndpoints(missingEndpoints)
        .setChangedOperations(changedOperations)
        .setChangedExtensions(changedExtensions);

    String expected =
        "ChangedOpenApi(oldSpecOpenApi="
            + oldSpecOpenApi
            + ", newSpecOpenApi="
            + newSpecOpenApi
            + ", newEndpoints="
            + newEndpoints
            + ", missingEndpoints="
            + missingEndpoints
            + ", changedOperations="
            + changedOperations
            + ", changedExtensions="
            + changedExtensions
            + ")";
    assertEquals(expected, changedOpenApi.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    CompositeConfiguration config = new CompositeConfiguration();
    ChangedOpenApi changedOpenApi2 = new ChangedOpenApi(new OpenApiDiffOptions(config));
    changedOpenApi2
        .setOldSpecOpenApi(oldSpecOpenApi)
        .setNewSpecOpenApi(newSpecOpenApi)
        .setNewEndpoints(newEndpoints)
        .setMissingEndpoints(missingEndpoints)
        .setChangedOperations(changedOperations)
        .setChangedExtensions(changedExtensions);

    assertTrue(changedOpenApi.equals(changedOpenApi2));
    assertEquals(changedOpenApi.hashCode(), changedOpenApi2.hashCode());
  }
}
