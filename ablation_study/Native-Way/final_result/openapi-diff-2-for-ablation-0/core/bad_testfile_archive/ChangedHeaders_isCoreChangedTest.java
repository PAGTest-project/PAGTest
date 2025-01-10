package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedHeaders_isCoreChangedTest {
  private ChangedHeaders changedHeaders;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    Map<String, ChangedHeaders.Header> oldHeaders = new HashMap<>();
    Map<String, ChangedHeaders.Header> newHeaders = new HashMap<>();
    context = new DiffContext(new OpenApiDiffOptions());
    changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
  }

  @Test
  public void testIsCoreChangedNoChanges() {
    changedHeaders.setIncreased(new HashMap<>());
    changedHeaders.setMissing(new HashMap<>());
    assertEquals(DiffResult.NO_CHANGES, changedHeaders.isCoreChanged());
  }

  @Test
  public void testIsCoreChangedCompatible() {
    Map<String, ChangedHeaders.Header> increased = new HashMap<>();
    increased.put("header1", new ChangedHeaders.Header());
    changedHeaders.setIncreased(increased);
    changedHeaders.setMissing(new HashMap<>());
    assertEquals(DiffResult.COMPATIBLE, changedHeaders.isCoreChanged());
  }

  @Test
  public void testIsCoreChangedIncompatible() {
    Map<String, ChangedHeaders.Header> missing = new HashMap<>();
    missing.put("header1", new ChangedHeaders.Header());
    changedHeaders.setIncreased(new HashMap<>());
    changedHeaders.setMissing(missing);
    context.setResponse(true);
    assertEquals(DiffResult.INCOMPATIBLE, changedHeaders.isCoreChanged());
  }

  @Test
  public void testIsCoreChangedIncompatibleNotEnabled() {
    Map<String, ChangedHeaders.Header> missing = new HashMap<>();
    missing.put("header1", new ChangedHeaders.Header());
    changedHeaders.setIncreased(new HashMap<>());
    changedHeaders.setMissing(missing);
    context.setResponse(false);
    assertEquals(DiffResult.COMPATIBLE, changedHeaders.isCoreChanged());
  }
}
