package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.schema.ChangedRequired;

public class ChangedSchema_setRequiredTest {

  @Test
  public void testSetRequired() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    ChangedRequired required = new ChangedRequired(null, null, null);

    // When
    ChangedSchema result = changedSchema.setRequired(required);

    // Then
    assertNotNull(result);
    assertEquals(required, changedSchema.getRequired());
  }
}
