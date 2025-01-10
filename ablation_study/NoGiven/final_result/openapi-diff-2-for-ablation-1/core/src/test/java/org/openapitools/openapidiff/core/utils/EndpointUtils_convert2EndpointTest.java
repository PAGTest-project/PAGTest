package org.openapitools.openapidiff.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Endpoint;

public class EndpointUtils_convert2EndpointTest {

  @Test
  public void testConvert2Endpoint() {
    // Given
    String pathUrl = "/test";
    PathItem.HttpMethod httpMethod = PathItem.HttpMethod.GET;
    Operation operation = new Operation();
    operation.setSummary("Test Summary");

    // When
    Endpoint endpoint = EndpointUtils.convert2Endpoint(pathUrl, httpMethod, operation);

    // Then
    assertNotNull(endpoint);
    assertEquals(pathUrl, endpoint.getPathUrl());
    assertEquals(httpMethod, endpoint.getMethod());
    assertEquals("Test Summary", endpoint.getSummary());
    assertEquals(operation, endpoint.getOperation());
  }
}
