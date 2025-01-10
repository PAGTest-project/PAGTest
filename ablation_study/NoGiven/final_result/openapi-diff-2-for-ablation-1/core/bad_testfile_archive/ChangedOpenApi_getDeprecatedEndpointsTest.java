package org.openapitools.openapidiff.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_getDeprecatedEndpointsTest {

  private ChangedOpenApi changedOpenApi;
  private ChangedOperation deprecatedOperation;
  private ChangedOperation nonDeprecatedOperation;

  @BeforeEach
  public void setUp() {
    CompositeConfiguration config = new CompositeConfiguration();
    changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions(config));
    deprecatedOperation =
        new ChangedOperation("/path", PathItem.HttpMethod.GET, new Operation(), new Operation());
    deprecatedOperation.setDeprecated(true);
    nonDeprecatedOperation =
        new ChangedOperation("/path", PathItem.HttpMethod.POST, new Operation(), new Operation());
    nonDeprecatedOperation.setDeprecated(false);
    changedOpenApi.setChangedOperations(Arrays.asList(deprecatedOperation, nonDeprecatedOperation));
  }

  @Test
  public void testGetDeprecatedEndpoints_withDeprecatedOperations() {
    List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
    assertThat(deprecatedEndpoints).hasSize(1);
    assertThat(deprecatedEndpoints.get(0).getPathUrl()).isEqualTo(deprecatedOperation.getPathUrl());
    assertThat(deprecatedEndpoints.get(0).getMethod())
        .isEqualTo(deprecatedOperation.getHttpMethod());
  }

  @Test
  public void testGetDeprecatedEndpoints_withoutDeprecatedOperations() {
    changedOpenApi.setChangedOperations(Arrays.asList(nonDeprecatedOperation));
    List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
    assertThat(deprecatedEndpoints).isEmpty();
  }

  @Test
  public void testGetDeprecatedEndpoints_withEmptyOperations() {
    changedOpenApi.setChangedOperations(Arrays.asList());
    List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
    assertThat(deprecatedEndpoints).isEmpty();
  }
}
