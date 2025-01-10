package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.schema.ChangedReadOnly;

class ChangedSchema_setReadOnlyTest {

  @Test
  void testSetReadOnly() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    ChangedReadOnly readOnly = new ChangedReadOnly(false, true, null);

    // When
    changedSchema.setReadOnly(readOnly);

    // Then
    assertEquals(readOnly, changedSchema.getReadOnly());
  }
}
