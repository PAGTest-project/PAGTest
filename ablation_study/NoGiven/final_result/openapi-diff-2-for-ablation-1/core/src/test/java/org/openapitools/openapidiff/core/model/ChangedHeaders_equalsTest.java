package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.headers.Header;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedHeaders_equalsTest {

  @Test
  public void testEquals_SameObject() {
    ChangedHeaders headers =
        new ChangedHeaders(new HashMap<>(), new HashMap<>(), new DiffContext(null));
    assertTrue(headers.equals(headers));
  }

  @Test
  public void testEquals_NullObject() {
    ChangedHeaders headers =
        new ChangedHeaders(new HashMap<>(), new HashMap<>(), new DiffContext(null));
    assertFalse(headers.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    ChangedHeaders headers =
        new ChangedHeaders(new HashMap<>(), new HashMap<>(), new DiffContext(null));
    assertFalse(headers.equals(new Object()));
  }

  @Test
  public void testEquals_DifferentState() {
    Map<String, Header> oldHeaders = new HashMap<>();
    Map<String, Header> newHeaders = new HashMap<>();
    DiffContext context = new DiffContext(null);

    ChangedHeaders headers1 =
        new ChangedHeaders(oldHeaders, newHeaders, context)
            .setIncreased(new HashMap<>())
            .setMissing(new HashMap<>())
            .setChanged(new HashMap<>());

    ChangedHeaders headers2 =
        new ChangedHeaders(oldHeaders, newHeaders, context)
            .setIncreased(new HashMap<>())
            .setMissing(new HashMap<>())
            .setChanged(new HashMap<>());

    assertTrue(headers1.equals(headers2));

    Map<String, Header> increasedMap = new HashMap<>();
    increasedMap.put("key", new Header());
    headers2.setIncreased(increasedMap);
    assertFalse(headers1.equals(headers2));
  }

  @Test
  public void testHashCode_Consistency() {
    Map<String, Header> oldHeaders = new HashMap<>();
    Map<String, Header> newHeaders = new HashMap<>();
    DiffContext context = new DiffContext(null);

    ChangedHeaders headers =
        new ChangedHeaders(oldHeaders, newHeaders, context)
            .setIncreased(new HashMap<>())
            .setMissing(new HashMap<>())
            .setChanged(new HashMap<>());

    int hashCode1 = headers.hashCode();
    int hashCode2 = headers.hashCode();

    assertEquals(hashCode1, hashCode2);
  }
}
