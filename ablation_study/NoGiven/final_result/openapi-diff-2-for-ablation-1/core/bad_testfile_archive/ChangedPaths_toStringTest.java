package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.PathItem;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedPaths_toStringTest {

  @Test
  public void testToString() {
    Map<String, PathItem> oldPathMap = new HashMap<>();
    Map<String, PathItem> newPathMap = new HashMap<>();
    Map<String, PathItem> increased = new HashMap<>();
    Map<String, PathItem> missing = new HashMap<>();
    Map<String, ChangedPath> changed = new HashMap<>();

    CompositeConfiguration config = new CompositeConfiguration();
    OpenApiDiffOptions options = new OpenApiDiffOptions(config);

    ChangedPaths changedPaths = new ChangedPaths(oldPathMap, newPathMap, options);
    changedPaths.setIncreased(increased);
    changedPaths.setMissing(missing);
    changedPaths.setChanged(changed);

    String expected =
        "ChangedPaths(oldPathMap={}, newPathMap={}, increased={}, missing={}, changed={})";
    assertEquals(expected, changedPaths.toString());
  }
}
