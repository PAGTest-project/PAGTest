package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openapitools.openapidiff.core.model.BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_isCoreChangedTest {

  private ChangedOpenApi changedOpenApi;
  private OpenApiDiffOptions options;

  @BeforeEach
  public void setUp() {
    options = OpenApiDiffOptions.builder().build();
    changedOpenApi = new ChangedOpenApi(options);
  }

  @Test
  public void testIsCoreChanged_NoChanges() {
    changedOpenApi.setNewEndpoints(Collections.emptyList());
    changedOpenApi.setMissingEndpoints(Collections.emptyList());
    assertEquals(DiffResult.NO_CHANGES, changedOpenApi.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_Compatible() {
    List<Endpoint> newEndpoints = Collections.singletonList(new Endpoint());
    changedOpenApi.setNewEndpoints(newEndpoints);
    changedOpenApi.setMissingEndpoints(Collections.emptyList());
    assertEquals(DiffResult.COMPATIBLE, changedOpenApi.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_Incompatible() {
    List<Endpoint> missingEndpoints = Collections.singletonList(new Endpoint());
    changedOpenApi.setNewEndpoints(Collections.emptyList());
    changedOpenApi.setMissingEndpoints(missingEndpoints);
    assertEquals(DiffResult.INCOMPATIBLE, changedOpenApi.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_Incompatible_EndpointsDecreasedDisabled() {
    List<Endpoint> missingEndpoints = Collections.singletonList(new Endpoint());
    changedOpenApi.setNewEndpoints(Collections.emptyList());
    changedOpenApi.setMissingEndpoints(missingEndpoints);
    options.getConfig().setProperty(OPENAPI_ENDPOINTS_DECREASED.name(), "false");
    assertEquals(DiffResult.COMPATIBLE, changedOpenApi.isCoreChanged());
  }
}
