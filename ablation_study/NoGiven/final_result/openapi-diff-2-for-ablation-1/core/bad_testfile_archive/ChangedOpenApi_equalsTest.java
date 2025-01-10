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

public class ChangedOpenApi_equalsTest {

  private ChangedOpenApi changedOpenApi1;
  private ChangedOpenApi changedOpenApi2;
  private OpenAPI oldSpecOpenApi;
  private OpenAPI newSpecOpenApi;
  private List<Endpoint> newEndpoints;
  private List<Endpoint> missingEndpoints;
  private List<ChangedOperation> changedOperations;
  private ChangedExtensions changedExtensions;

  @BeforeEach
  public void setUp() {
    CompositeConfiguration config = new CompositeConfiguration();
    OpenApiDiffOptions options = OpenApiDiffOptions.fromConfig(config);
    changedOpenApi1 = new ChangedOpenApi(options);
    changedOpenApi2 = new ChangedOpenApi(options);

    oldSpecOpenApi = new OpenAPI();
    newSpecOpenApi = new OpenAPI();
    newEndpoints = Collections.emptyList();
    missingEndpoints = Collections.emptyList();
    changedOperations = Collections.emptyList();
    changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);

    changedOpenApi1
        .setOldSpecOpenApi(oldSpecOpenApi)
        .setNewSpecOpenApi(newSpecOpenApi)
        .setNewEndpoints(newEndpoints)
        .setMissingEndpoints(missingEndpoints)
        .setChangedOperations(changedOperations)
        .setChangedExtensions(changedExtensions);

    changedOpenApi2
        .setOldSpecOpenApi(oldSpecOpenApi)
        .setNewSpecOpenApi(newSpecOpenApi)
        .setNewEndpoints(newEndpoints)
        .setMissingEndpoints(missingEndpoints)
        .setChangedOperations(changedOperations)
        .setChangedExtensions(changedExtensions);
  }

  @Test
  public void testEquals_SameInstance() {
    assertTrue(changedOpenApi1.equals(changedOpenApi1));
  }

  @Test
  public void testEquals_EqualObjects() {
    assertTrue(changedOpenApi1.equals(changedOpenApi2));
  }

  @Test
  public void testEquals_DifferentOldSpecOpenApi() {
    changedOpenApi2.setOldSpecOpenApi(new OpenAPI());
    assertEquals(false, changedOpenApi1.equals(changedOpenApi2));
  }

  @Test
  public void testEquals_DifferentNewSpecOpenApi() {
    changedOpenApi2.setNewSpecOpenApi(new OpenAPI());
    assertEquals(false, changedOpenApi1.equals(changedOpenApi2));
  }

  @Test
  public void testEquals_DifferentNewEndpoints() {
    changedOpenApi2.setNewEndpoints(Collections.singletonList(new Endpoint()));
    assertEquals(false, changedOpenApi1.equals(changedOpenApi2));
  }

  @Test
  public void testEquals_DifferentMissingEndpoints() {
    changedOpenApi2.setMissingEndpoints(Collections.singletonList(new Endpoint()));
    assertEquals(false, changedOpenApi1.equals(changedOpenApi2));
  }

  @Test
  public void testEquals_DifferentChangedOperations() {
    changedOpenApi2.setChangedOperations(
        Collections.singletonList(
            new ChangedOperation(
                "path",
                io.swagger.v3.oas.models.PathItem.HttpMethod.GET,
                new io.swagger.v3.oas.models.Operation(),
                new io.swagger.v3.oas.models.Operation())));
    assertEquals(false, changedOpenApi1.equals(changedOpenApi2));
  }

  @Test
  public void testEquals_DifferentChangedExtensions() {
    changedOpenApi2.setChangedExtensions(
        new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null));
    assertEquals(false, changedOpenApi1.equals(changedOpenApi2));
  }
}
