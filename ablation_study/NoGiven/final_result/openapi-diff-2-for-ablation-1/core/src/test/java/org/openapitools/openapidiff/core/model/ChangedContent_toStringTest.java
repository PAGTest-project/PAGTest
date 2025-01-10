package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedContent_toStringTest {

  @Test
  public void testToString() {
    Content oldContent = new Content();
    Content newContent = new Content();
    DiffContext context = new DiffContext(null); // Provide a default value for OpenApiDiffOptions
    Map<String, MediaType> increased = new HashMap<>();
    Map<String, MediaType> missing = new HashMap<>();
    Map<String, ChangedMediaType> changed = new HashMap<>();

    ChangedContent changedContent =
        new ChangedContent(oldContent, newContent, context)
            .setIncreased(increased)
            .setMissing(missing)
            .setChanged(changed);

    String expected =
        "ChangedContent(oldContent="
            + oldContent
            + ", newContent="
            + newContent
            + ", context="
            + context
            + ", increased="
            + increased
            + ", missing="
            + missing
            + ", changed="
            + changed
            + ")";
    assertEquals(expected, changedContent.toString());
  }
}
