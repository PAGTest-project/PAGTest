package org.openapitools.openapidiff.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Endpoint;

public class EndpointUtils_convert2EndpointsTest {

  @Test
  public void testConvert2Endpoints_NullMap() {
    // Given
    String pathUrl = "/test";
    Map<PathItem.HttpMethod, Operation> map = null;

    // When
    Collection<Endpoint> result = EndpointUtils.convert2Endpoints(pathUrl, map);

    // Then
    assertTrue(result.isEmpty());
  }

  @Test
  public void testConvert2Endpoints_NonEmptyMap() {
    // Given
    String pathUrl = "/test";
    Map<PathItem.HttpMethod, Operation> map = new HashMap<>();
    Operation operation = new Operation();
    operation.setSummary("Test Operation");
    map.put(PathItem.HttpMethod.GET, operation);

    // When
    Collection<Endpoint> result = EndpointUtils.convert2Endpoints(pathUrl, map);

    // Then
    assertEquals(1, result.size());
    Endpoint endpoint = result.iterator().next();
    assertEquals(pathUrl, endpoint.getPathUrl());
    assertEquals(PathItem.HttpMethod.GET, endpoint.getMethod());
    assertEquals("Test Operation", endpoint.getSummary());
  }
}
