
package org.openapitools.openapidiff.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class Copy_copyMapTest {

  @Test
  public void testCopyMap_withNullMap() {
    Map<String, String> originalMap = null;
    Map<String, String> copiedMap = Copy.copyMap(originalMap);

    assertNotNull(copiedMap);
    assertEquals(0, copiedMap.size());
  }

  @Test
  public void testCopyMap_withNonEmptyMap() {
    Map<String, String> originalMap = new HashMap<>();
    originalMap.put("key1", "value1");
    originalMap.put("key2", "value2");

    Map<String, String> copiedMap = Copy.copyMap(originalMap);

    assertNotNull(copiedMap);
    assertEquals(originalMap.size(), copiedMap.size());
    assertEquals(originalMap, copiedMap);
  }
}
