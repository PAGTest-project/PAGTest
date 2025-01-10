package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;

class Endpoint_equalsTest {

  @Test
  void testEquals_SameInstance() {
    Endpoint endpoint = new Endpoint();
    assertTrue(endpoint.equals(endpoint));
  }

  @Test
  void testEquals_NullObject() {
    Endpoint endpoint = new Endpoint();
    assertFalse(endpoint.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    Endpoint endpoint = new Endpoint();
    Object obj = new Object();
    assertFalse(endpoint.equals(obj));
  }

  @Test
  void testEquals_DifferentFields() {
    Endpoint endpoint1 = new Endpoint();
    endpoint1.setPathUrl("path1");
    endpoint1.setMethod(PathItem.HttpMethod.GET);
    endpoint1.setSummary("summary1");
    endpoint1.setPath(new PathItem());
    endpoint1.setOperation(new Operation());

    Endpoint endpoint2 = new Endpoint();
    endpoint2.setPathUrl("path2");
    endpoint2.setMethod(PathItem.HttpMethod.POST);
    endpoint2.setSummary("summary2");
    endpoint2.setPath(new PathItem());
    endpoint2.setOperation(new Operation());

    assertFalse(endpoint1.equals(endpoint2));
  }

  @Test
  void testEquals_SameFields() {
    Endpoint endpoint1 = new Endpoint();
    endpoint1.setPathUrl("path");
    endpoint1.setMethod(PathItem.HttpMethod.GET);
    endpoint1.setSummary("summary");
    endpoint1.setPath(new PathItem());
    endpoint1.setOperation(new Operation());

    Endpoint endpoint2 = new Endpoint();
    endpoint2.setPathUrl("path");
    endpoint2.setMethod(PathItem.HttpMethod.GET);
    endpoint2.setSummary("summary");
    endpoint2.setPath(new PathItem());
    endpoint2.setOperation(new Operation());

    assertTrue(endpoint1.equals(endpoint2));
  }

  @Test
  void testHashCode_Consistency() {
    Endpoint endpoint1 = new Endpoint();
    endpoint1.setPathUrl("path");
    endpoint1.setMethod(PathItem.HttpMethod.GET);
    endpoint1.setSummary("summary");
    endpoint1.setPath(new PathItem());
    endpoint1.setOperation(new Operation());

    Endpoint endpoint2 = new Endpoint();
    endpoint2.setPathUrl("path");
    endpoint2.setMethod(PathItem.HttpMethod.GET);
    endpoint2.setSummary("summary");
    endpoint2.setPath(new PathItem());
    endpoint2.setOperation(new Operation());

    assertEquals(endpoint1.hashCode(), endpoint2.hashCode());
  }
}
