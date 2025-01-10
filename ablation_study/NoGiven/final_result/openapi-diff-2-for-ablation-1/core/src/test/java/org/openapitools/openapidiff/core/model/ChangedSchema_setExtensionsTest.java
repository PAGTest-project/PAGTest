package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ChangedSchema_setExtensionsTest {

  @Test
  public void testSetExtensions() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    ChangedExtensions extensions = new ChangedExtensions(null, null, null);

    // When
    ChangedSchema result = changedSchema.setExtensions(extensions);

    // Then
    assertNotNull(result);
    assertEquals(extensions, changedSchema.getExtensions());
  }
}
