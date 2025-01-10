package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedSchema_getChangedElementsTest {

  private ChangedSchema changedSchema;
  private OpenApiDiffOptions options;

  @BeforeEach
  public void setUp() {
    changedSchema = new ChangedSchema();
    options = new OpenApiDiffOptions(new CompositeConfiguration());
  }

  @Test
  public void testGetChangedElements_FirstCall() {
    // Given
    changedSchema.setContext(new DiffContext(options));

    // When
    List<Changed> result = changedSchema.getChangedElements();

    // Then
    assertTrue(result.isEmpty());
  }

  @Test
  public void testGetChangedElements_SubsequentCall() {
    // Given
    changedSchema.setContext(new DiffContext(options));
    changedSchema.getChangedElements(); // First call to initialize changedElements

    // When
    List<Changed> result = changedSchema.getChangedElements();

    // Then
    assertEquals(Collections.emptyList(), result);
  }
}
