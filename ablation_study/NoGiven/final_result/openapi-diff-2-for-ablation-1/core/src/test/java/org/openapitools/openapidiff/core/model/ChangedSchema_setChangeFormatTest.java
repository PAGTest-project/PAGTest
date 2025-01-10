package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChangedSchema_setChangeFormatTest {

  @Test
  public void testSetChangeFormat() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();

    // When
    ChangedSchema result = changedSchema.setChangeFormat(true);

    // Then
    assertTrue(changedSchema.isChangeFormat());
    assertEquals(changedSchema, result);
  }
}
