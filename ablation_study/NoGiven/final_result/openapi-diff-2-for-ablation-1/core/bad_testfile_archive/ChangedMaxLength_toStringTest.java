package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ChangedMaxLength_toStringTest {

  @Test
  public void testToString() {
    Integer oldValue = 10;
    Integer newValue = 20;
    CompositeConfiguration config = new CompositeConfiguration();
    OpenApiDiffOptions options = new OpenApiDiffOptions(config);
    DiffContext context = new DiffContext(options);
    ChangedMaxLength changedMaxLength = new ChangedMaxLength(oldValue, newValue, context);

    String expected =
        "ChangedMaxLength(oldValue="
            + oldValue
            + ", newValue="
            + newValue
            + ", context="
            + context
            + ")";
    assertEquals(expected, changedMaxLength.toString());
  }
}
