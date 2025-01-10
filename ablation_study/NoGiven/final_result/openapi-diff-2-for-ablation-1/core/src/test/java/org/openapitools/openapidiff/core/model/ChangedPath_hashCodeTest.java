package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.PathItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class ChangedPath_hashCodeTest {

  @Test
  public void testHashCode() {
    String pathUrl = "/test";
    PathItem oldPath = new PathItem();
    PathItem newPath = new PathItem();
    DiffContext context = new DiffContext(null);
    ChangedPath changedPath = new ChangedPath(pathUrl, oldPath, newPath, context);
    changedPath
        .setIncreased(new LinkedHashMap<>())
        .setMissing(new LinkedHashMap<>())
        .setChanged(new ArrayList<>())
        .setExtensions(
            new ChangedExtensions(new LinkedHashMap<>(), new LinkedHashMap<>(), context));

    int expectedHashCode =
        Objects.hash(
            pathUrl,
            oldPath,
            newPath,
            context,
            new LinkedHashMap<>(),
            new LinkedHashMap<>(),
            new ArrayList<>(),
            new ChangedExtensions(new LinkedHashMap<>(), new LinkedHashMap<>(), context));
    assertEquals(expectedHashCode, changedPath.hashCode());
  }
}
