package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

public class ChangedSchema_hashCodeTest {

  @Test
  public void testHashCode() {
    ChangedSchema changedSchema = new ChangedSchema();
    changedSchema.setContext(new DiffContext(null));
    changedSchema.setOldSchema(new Schema<>());
    changedSchema.setNewSchema(new Schema<>());

    int expectedHashCode = changedSchema.hashCode();

    assertEquals(expectedHashCode, changedSchema.hashCode());
  }
}
