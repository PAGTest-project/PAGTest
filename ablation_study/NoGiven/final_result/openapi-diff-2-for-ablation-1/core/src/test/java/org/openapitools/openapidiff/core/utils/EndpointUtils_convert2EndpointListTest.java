package org.openapitools.openapidiff.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Endpoint;

public class EndpointUtils_convert2EndpointListTest {

  @Test
  public void testConvert2EndpointList_NullMap() {
    // Given
    Map<String, PathItem> map = null;

    // When
    List<Endpoint> result = EndpointUtils.convert2EndpointList(map);

    // Then
    assertTrue(result.isEmpty());
  }

  @Test
  public void testConvert2EndpointList_NonEmptyMap() {
    // Given
    Map<String, PathItem> map = new HashMap<>();
    PathItem pathItem = new PathItem();
    Operation operation = new Operation();
    operation.setSummary("Test Summary");
    pathItem.setGet(operation);
    map.put("/test", pathItem);

    // When
    List<Endpoint> result = EndpointUtils.convert2EndpointList(map);

    // Then
    assertEquals(1, result.size());
    Endpoint endpoint = result.get(0);
    assertEquals("/test", endpoint.getPathUrl());
    assertEquals(PathItem.HttpMethod.GET, endpoint.getMethod());
    assertEquals("Test Summary", endpoint.getSummary());
    assertEquals(pathItem, endpoint.getPath());
    assertEquals(operation, endpoint.getOperation());
  }
}
