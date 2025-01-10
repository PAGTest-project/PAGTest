package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.headers.Header;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedHeaders_toStringTest {

  private ChangedHeaders changedHeaders;
  private Map<String, Header> oldHeaders;
  private Map<String, Header> newHeaders;
  private DiffContext context;
  private Map<String, Header> increased;
  private Map<String, Header> missing;
  private Map<String, ChangedHeader> changed;

  @BeforeEach
  public void setUp() {
    oldHeaders = new HashMap<>();
    newHeaders = new HashMap<>();
    context = new DiffContext(new OpenApiDiffOptions());
    increased = new HashMap<>();
    missing = new HashMap<>();
    changed = new HashMap<>();

    // Initialize headers for testing
    oldHeaders.put("header1", new Header());
    newHeaders.put("header2", new Header());
    increased.put("header3", new Header());
    missing.put("header4", new Header());
    changed.put("header5", new ChangedHeader(new Header(), new Header(), context));

    changedHeaders =
        new ChangedHeaders(oldHeaders, newHeaders, context)
            .setIncreased(increased)
            .setMissing(missing)
            .setChanged(changed);
  }

  @Test
  public void testToString() {
    String expected =
        "ChangedHeaders(oldHeaders={header1=io.swagger.v3.oas.models.headers.Header@}, newHeaders={header2=io.swagger.v3.oas.models.headers.Header@}, context=org.openapitools.openapidiff.core.model.DiffContext@, increased={header3=io.swagger.v3.oas.models.headers.Header@}, missing={header4=io.swagger.v3.oas.models.headers.Header@}, changed={header5=org.openapitools.openapidiff.core.model.ChangedHeader@})";
    assertEquals(expected, changedHeaders.toString());
  }
}
