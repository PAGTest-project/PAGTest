package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

public class ChangedSchema_isCoreChangedTest {

  @Test
  public void testIsCoreChanged_CoreChangedIsNull() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    changedSchema.setContext(new DiffContext());
    changedSchema.setOldSchema(new Schema<>());
    changedSchema.setNewSchema(new Schema<>());

    // When
    DiffResult result = changedSchema.isCoreChanged();

    // Then
    assertEquals(DiffResult.NO_CHANGES, result);
  }

  @Test
  public void testIsCoreChanged_CoreChangedIsNotNull() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    changedSchema.setContext(new DiffContext());
    changedSchema.setOldSchema(new Schema<>());
    changedSchema.setNewSchema(new Schema<>());
    changedSchema.coreChanged = DiffResult.INCOMPATIBLE;

    // When
    DiffResult result = changedSchema.isCoreChanged();

    // Then
    assertEquals(DiffResult.INCOMPATIBLE, result);
  }
}
